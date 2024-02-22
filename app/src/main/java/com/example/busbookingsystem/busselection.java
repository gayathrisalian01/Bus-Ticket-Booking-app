package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class busselection extends AppCompatActivity {
    ListView listView;
    DatabaseHandler databaseHandler;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busselection);
        listView = findViewById(R.id.busListView);
        databaseHandler = new DatabaseHandler(this);
        TextView from=findViewById(R.id.txtfrom);
        TextView to=findViewById(R.id.txtto);
        TextView dateTextView=findViewById(R.id.txtdate);
        back = findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(busselection.this, ChooseDestination.class);
                startActivity(in);
            }
        });

        Intent intent = getIntent();
        String source=intent.getStringExtra("sourceEditText");
        String destination = intent.getStringExtra("destinationEditText");
        String date = intent.getStringExtra("date");

        ImageView imgFilter=findViewById(R.id.imgFilter);
        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(busselection.this, FilterActivity.class);
                intent.putExtra("source",source);
                intent.putExtra("destination", destination);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

        from.setText(source);
        to.setText(destination);
        dateTextView.setText(date);
        ArrayList<busdetails> busList = (ArrayList<busdetails>) getIntent().getSerializableExtra("AvailableBuses");

        CustomListAdapter adapter = new CustomListAdapter(this, busList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                busdetails selectedBus = (busdetails) listView.getAdapter().getItem(position);
                SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                if ((sharedPreferences.getInt("userId", 0)) == -1) {
                    Toast.makeText(getApplicationContext(),"Login to Book Seats!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(busselection.this, LoginPage.class));
                } else {
                    // Create an intent to start the seatselection activity
                    Intent intent = new Intent(busselection.this, seatselection.class);

                    // Pass the selected bus details as extras in the intent
                    intent.putExtra("busId", String.valueOf(selectedBus.getBusId()));
                    intent.putExtra("sourcek", source.toString());
                    intent.putExtra("destink", destination.toString());
                    intent.putExtra("TravelNamek", selectedBus.getTravelName());
                    intent.putExtra("Timek", selectedBus.getTime());
                    intent.putExtra("Pricek", selectedBus.getPrice());
                    intent.putExtra("Date", date.toString());
                    intent.putExtra("Durationk", selectedBus.getDuration());
                    intent.putExtra("Typek", selectedBus.getType());

                    // Start the seatselection activity with the intent
                    startActivity(intent);
                }
            }
        });

    }
    public class CustomListAdapter extends BaseAdapter {
        private ArrayList<busdetails> listData;
        private LayoutInflater layoutInflater;

        public CustomListAdapter(Context aContext, ArrayList<busdetails> listData) {
            this.listData = listData;
            layoutInflater = LayoutInflater.from(aContext);
        }

        public int getCount() {
            return listData.size();
        }

        public Object getItem(int position) {
            return listData.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView uTime, uTravelName, uPrice, uDuration, uType;
        }

        @Override
        public View getView(int position, View v, ViewGroup vg) {
            ViewHolder holder;
            if (v == null) {
                v = layoutInflater.inflate(R.layout.buslist, null);
                holder = new ViewHolder();
                holder.uTime = v.findViewById(R.id.lstTime);
                holder.uTravelName = v.findViewById(R.id.lstTravelsName);
                holder.uPrice = v.findViewById(R.id.lstPrice);
                holder.uDuration = v.findViewById(R.id.lstDuration);
                holder.uType = v.findViewById(R.id.lstType);

                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            holder.uTime.setText(listData.get(position).getTime());
            holder.uTravelName.setText(listData.get(position).getTravelName());
            holder.uPrice.setText(listData.get(position).getPrice());
            holder.uDuration.setText(listData.get(position).getDuration());
            holder.uType.setText(listData.get(position).getType());

            return v;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(busselection.this, ChooseDestination.class);
        startActivity(in);
    }
}