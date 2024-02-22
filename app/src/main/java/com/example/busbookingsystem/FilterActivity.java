package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");;
    Button btnApply;
    Button ac,non_ac;
    RadioGroup sortby;
    RadioButton sortPrice, sortEarlyDepature, sortLateDeparture;
    DatabaseHandler db=new DatabaseHandler(this);
    ArrayList<busdetails> availableBuses;
    ArrayList<busdetails> filterBuses;
    ArrayList<busdetails> typeBuses;
    String source,destination,date;

    private LinearLayout linearLayout;

    ImageView morning, afternoon, night;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        btnApply=(Button) findViewById(R.id.btnApply);
        ac = findViewById(R.id.btnAC);
        non_ac = findViewById(R.id.btnNonAC);
        sortby=findViewById(R.id.rgSort);
        sortPrice=findViewById(R.id.rbPrice);
        sortEarlyDepature=findViewById(R.id.rbEarly);
        sortLateDeparture=findViewById(R.id.rbLate);
        morning=findViewById(R.id.imgMorning);
        night=findViewById(R.id.imgNight);
        afternoon=findViewById(R.id.imgAfternoon);

        linearLayout=findViewById(R.id.LL);

        Intent in=getIntent();
        source=in.getStringExtra("source");
        destination=in.getStringExtra("destination");
        date=in.getStringExtra("date");

//        source="Udupi";
//        destination="Bengaluru";
//        date="20-12-2023";

        availableBuses=db.getAvailableBuses(source,destination,date);
        filterBuses= db.getAvailableBuses(source,destination,date);
        typeBuses=db.getAvailableBuses(source,destination,date);

        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<busdetails> temp=db.getAvailableBuses(source,destination,date);
                ArrayList<busdetails> morningBuses = new ArrayList<>();
                for (busdetails bus : temp) {
                    try {
                        String[] timeParts = bus.getTime().split("-");
                        String startTime = timeParts[0];

                        // Parse the start time
                        Date timeStart = TIME_FORMAT.parse(startTime);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(timeStart);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);

                        // Check if the start time is between 6:00 and 11:59
                        if (hour >= 6 && hour <= 11) {
                            morningBuses.add(bus);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                filterBuses.clear();
                filterBuses.addAll(morningBuses);
                morning.setBackground(getDrawable(R.drawable.custom_buttonbackground_selected));
                afternoon.setBackground(getDrawable(R.drawable.custom_buttonbackground_unselected));
                night.setBackground(getDrawable(R.drawable.custom_buttonbackground_unselected));
            }
        });

        afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<busdetails> temp=db.getAvailableBuses(source,destination,date);
                ArrayList<busdetails> afternoonBuses = new ArrayList<>();
                for (busdetails bus : temp) {
                    try {
                        String[] timeParts = bus.getTime().split("-");
                        String startTime = timeParts[0];

                        // Parse the start time
                        Date timeStart = TIME_FORMAT.parse(startTime);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(timeStart);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);

                        // Check if the start time is between 6:00 and 11:59
                        if (hour >= 12 && hour <= 17) {
                            afternoonBuses.add(bus);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                filterBuses.clear();
                filterBuses.addAll(afternoonBuses);
                morning.setBackground(getDrawable(R.drawable.custom_buttonbackground_unselected));
                afternoon.setBackground(getDrawable(R.drawable.custom_buttonbackground_selected));
                night.setBackground(getDrawable(R.drawable.custom_buttonbackground_unselected));
            }
        });


        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<busdetails> temp=db.getAvailableBuses(source,destination,date);
                ArrayList<busdetails> nightBuses = new ArrayList<>();
                for (busdetails bus : temp) {
                    try {
                        String[] timeParts = bus.getTime().split("-");
                        String startTime = timeParts[0];

                        // Parse the start time
                        Date timeStart = TIME_FORMAT.parse(startTime);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(timeStart);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);

                        // Check if the start time is between 6:00 and 11:59
                        if (hour >= 18 || hour <= 5) {
                            nightBuses.add(bus);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                filterBuses.clear();
                filterBuses.addAll(nightBuses);
                morning.setBackground(getDrawable(R.drawable.custom_buttonbackground_unselected));
                afternoon.setBackground(getDrawable(R.drawable.custom_buttonbackground_unselected));
                night.setBackground(getDrawable(R.drawable.custom_buttonbackground_selected));
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current background drawable
                Drawable background = btnApply.getBackground();
                // Set the alpha value (0-255) you desire (e.g., 128 for half-opacity)
                int alphaValue = 128;
                // Modify the alpha value of the background
                background.setAlpha(alphaValue);
                // Update the background of the button
                btnApply.setBackground(background);

                // Available Buses Updation taking common features both from type and filter array list
                availableBuses.clear();
                // Iterate through filterBuses
                for (busdetails bus : filterBuses) {
                    // Check if the bus is present in typeBuses
                    if (typeBuses.contains(bus)) {
                        // If the bus is present in both lists, add it to availableBuses
                        availableBuses.add(bus);
                    }
                }


                if(sortPrice.isChecked())
                {
                    Collections.sort(availableBuses, new Comparator<busdetails>() {
                        @Override
                        public int compare(busdetails bus1, busdetails bus2) {
                            // Parse the price and compare as integers
                            int price1 = Integer.parseInt(bus1.getPrice().substring(1));
                            int price2 = Integer.parseInt(bus2.getPrice().substring(1));

                            // Compare prices in ascending order
                            return price1 - price2;
                        }
                    });
                }
                if(sortEarlyDepature.isChecked())
                {
                    // Sort by early departure time
                    Collections.sort(availableBuses, new Comparator<busdetails>() {
                        @Override
                        public int compare(busdetails bus1, busdetails bus2) {
                            try {
                                String[] time1Parts = bus1.getTime().split("-");
                                String[] time2Parts = bus2.getTime().split("-");

                                Date time1Start = TIME_FORMAT.parse(time1Parts[0]);
                                Date time2Start = TIME_FORMAT.parse(time2Parts[0]);

                                return time1Start.compareTo(time2Start);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                }
                if(sortLateDeparture.isChecked())
                {
                    // Sort by late departure time
                    Collections.sort(availableBuses, new Comparator<busdetails>() {
                        @Override
                        public int compare(busdetails bus1, busdetails bus2) {
                            try {
                                String[] time1Parts = bus1.getTime().split("-");
                                String[] time2Parts = bus2.getTime().split("-");

                                Date time1Start = TIME_FORMAT.parse(time1Parts[0]);
                                Date time2Start = TIME_FORMAT.parse(time2Parts[0]);

                                return time2Start.compareTo(time1Start);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                }
                if(availableBuses.isEmpty()){
                   // Toast.makeText(getApplicationContext(),"No Buses Available!",Toast.LENGTH_LONG).show();
                    Snackbar snackbar;
                    Snackbar.make(linearLayout,"No Bus Available",Snackbar.LENGTH_INDEFINITE)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.colorAccent))
                            .show();
                }
                else {
                    Intent intent = new Intent(FilterActivity.this, busselection.class);
                    intent.putExtra("AvailableBuses", availableBuses);
                    intent.putExtra("sourceEditText", source);
                    intent.putExtra("destinationEditText", destination);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
            }
        });
        ac.setOnClickListener(this);
        non_ac.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnAC){
            ac.setBackground(getResources().getDrawable(R.drawable.button_curve_purple));
            ac.setTextColor(getResources().getColor(R.color.white));
            non_ac.setBackground(getResources().getDrawable(R.drawable.button_curve));
            non_ac.setTextColor(getResources().getColor(R.color.black));

            ArrayList<busdetails> temp=db.getAvailableBuses(source,destination,date);
            ArrayList<busdetails> acBuses = new ArrayList<>();
            for (busdetails bus : temp) {
                if(bus.getType().equals("AC"))
                {
                    acBuses.add(bus);
                }
            }
            typeBuses.clear();
            typeBuses.addAll(acBuses);
        }

        else if(view.getId() == R.id.btnNonAC){
            non_ac.setBackground(getResources().getDrawable(R.drawable.button_curve_purple));
            non_ac.setTextColor(getResources().getColor(R.color.white));
            ac.setBackground(getResources().getDrawable(R.drawable.button_curve));
            ac.setTextColor(getResources().getColor(R.color.black));

            ArrayList<busdetails> temp=db.getAvailableBuses(source,destination,date);
            ArrayList<busdetails> nonAcBuses = new ArrayList<>();
            for (busdetails bus : temp) {
                if(bus.getType().equals("NON AC"))
                {
                    nonAcBuses.add(bus);
                }
            }
            typeBuses.clear();
            typeBuses.addAll(nonAcBuses);
        }
    }
}