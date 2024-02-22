package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class seatselection extends AppCompatActivity {
    private ImageView[] seats; // An array to store the seat ImageView elements
    private boolean[] seatStatus; // An array to store the status of each seat
    private int numberOfSeats = 36; // Replace with the actual number of seats
    private int selectedSeat = -1; // Currently selected seat
    int seatNo;
   // private Random random = new Random();
    ArrayList<Integer> bookedBuses;
    Button seatContinue;
    ImageView back;

    private ConstraintLayout constraintLayout;

    DatabaseHandler db=new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seatselection);
        TextView dateTextView = findViewById(R.id.textdate);
        seatContinue=findViewById(R.id.seatselectNextbutton);
        constraintLayout=findViewById(R.id.CL);

        back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seatselection.this.finish();
            }
        });

//        // Get the current date
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
//
//        // Format the date using SimpleDateFormat
//        SimpleDateFormat sdf = new SimpleDateFormat("dd' - 'MMM' - 'yyyy' | 'EEEE", Locale.ENGLISH);
//        String formattedDate = sdf.format(currentDate);
//
//        // Set the formatted date to the TextView


        Intent intent = getIntent();
        int busId = Integer.parseInt(intent.getStringExtra("busId"));
        String from = intent.getStringExtra("sourcek");
        String to = intent.getStringExtra("destink");
        String time = intent.getStringExtra("Timek");
        String busName = intent.getStringExtra("TravelNamek");
        String price = intent.getStringExtra("Pricek");
        String date = intent.getStringExtra("Date");
        String duration = intent.getStringExtra("Durationk");
        String type = intent.getStringExtra("Typek");

// Now, you can set the retrieved data to your TextViews and other UI elements:
        TextView txtFrom = findViewById(R.id.txtfrom);
        TextView txtTo = findViewById(R.id.txtto);
        TextView txtduration = findViewById(R.id.txtduration);
        TextView txtType = findViewById(R.id.txttype);
        TextView txtTime = findViewById(R.id.txttime);
        TextView txtName = findViewById(R.id.txtname);
        TextView txtPrice = findViewById(R.id.txtprice);

        dateTextView.setText(date);
        txtFrom.setText(from);
        txtTo.setText(to);
        txtTime.setText(time);
        txtName.setText(busName);
        txtPrice.setText(price);
        txtduration.setText(duration);
        txtType.setText(type);

        seats = new ImageView[numberOfSeats];
        seatStatus = new boolean[numberOfSeats];
        bookedBuses=db.getBookedBuses(busId);

        // Initialize the seat statuses (for demonstration, randomly set some seats as booked)
        initializeSeatStatus();

        for (int i = 0; i < numberOfSeats; i++) {
            seats[i] = findViewById(getResources().getIdentifier("seat" + (i + 1), "id", getPackageName()));

            updateSeatImage(i); // Initial seat image setup

            if (!seatStatus[i]) {
                final int seatIndex = i;
                seats[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!seatStatus[seatIndex] && selectedSeat == -1) {
                            // Select the clicked seat if it's available and no seat is currently selected
                            selectedSeat = seatIndex;
                            updateSeatImage(seatIndex);
                            //Toast.makeText(seatselection.this,"You have selected seat " + (seatIndex + 1), Toast.LENGTH_SHORT).show();
                            Snackbar snackbar;
                            Snackbar.make(constraintLayout,"You have selected seat " + (seatIndex + 1),Snackbar.LENGTH_SHORT).show();
                            seatNo=seatIndex;

                        } else if (selectedSeat == seatIndex) {
                            // Deselect the currently selected seat if it's clicked again
                            selectedSeat = -1;
                            updateSeatImage(seatIndex);
                            //Toast.makeText(seatselection.this,"You have deselected seat " + (seatIndex + 1), Toast.LENGTH_SHORT).show();
                            Snackbar snackbar;
                            Snackbar.make(constraintLayout,"You have deselected seat " + (seatIndex + 1),Snackbar.LENGTH_SHORT).show();
                        }
                        else if(selectedSeat>-1){
                           // Toast.makeText(seatselection.this,"You can only book one seat at a time.", Toast.LENGTH_SHORT).show();
                            Snackbar snackbar;
                            Snackbar.make(constraintLayout,"You can only book one seat at a time",Snackbar.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }
        seatContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedSeat==-1) {
                    Snackbar snackbar;
                    Snackbar.make(constraintLayout,"Please select a seat",Snackbar.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(),"Please select a seat", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent=new Intent(seatselection.this, PassengersDetails.class );
                    intent.putExtra("busId",busId);
                    intent.putExtra("seatNo",seatNo);
                    intent.putExtra("source", from);
                    intent.putExtra("destination", to);
                    intent.putExtra("BusName", busName);
                    intent.putExtra("Time", time);
                    intent.putExtra("Price", price);
                    intent.putExtra("Date", date);
                    intent.putExtra("Duration", duration);
                    intent.putExtra("Type", type);

                    startActivity(intent);
                }
            }
        });
    }
    private void initializeSeatStatus() {
        // Initialize seat statuses with random booked seats
        Random random = new Random();
        for (int i = 0; i < numberOfSeats; i++) {
            // Simulate random seats as booked (for demonstration)
            seatStatus[i] = random.nextBoolean();
        }


        if(bookedBuses==null){
            for(int i=0; i<numberOfSeats; i++){
                seatStatus[i]=false;
            }
        }
        else{
            for(int i=0; i<numberOfSeats; i++){
                if(bookedBuses.contains(i)){
                    seatStatus[i]=true;
                }
                else{
                    seatStatus[i]=false;
                }
            }
        }
    }

    private void updateSeatImage(int seatIndex) {
        if (seatStatus[seatIndex]) {
            seats[seatIndex].setImageResource(R.drawable.booked_img);
        } else if (selectedSeat == seatIndex) {
            seats[seatIndex].setImageResource(R.drawable.your_seat_img);
        } else {
            seats[seatIndex].setImageResource(R.drawable.available_img);
        }
    }
}