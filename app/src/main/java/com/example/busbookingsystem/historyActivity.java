package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class historyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView li;
    Spinner spDisplay;
    ImageView back;
    DatabaseHandler db=new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        li = findViewById(R.id.historyList);
        spDisplay=findViewById(R.id.display1530);

        back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(historyActivity.this, settingsActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        int userId=sharedPreferences.getInt("userId", 0);
        ArrayList <PassengersDetailsClass> userList = db.getPassengersDetails(userId);
        li.setAdapter(new CustomListAdapter(this,userList));

        String dispContents[]={"All","15","30"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dispContents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDisplay.setAdapter(adapter);

        spDisplay.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        {
            SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
            int userId=sharedPreferences.getInt("userId", 0);
            String selectedItem = (String) adapterView.getItemAtPosition(i);
            if(selectedItem.equals("All")){
                ArrayList <PassengersDetailsClass> userList = db.getPassengersDetails(userId);
                li.setAdapter(new CustomListAdapter(this,userList));
            }
            else if (selectedItem.equals("15")) {
                ArrayList<PassengersDetailsClass> userList15 = db.getHistory15(userId);
                li.setAdapter(new CustomListAdapter(getApplicationContext(), userList15));
            }
            else if (selectedItem.equals("30")) {
                ArrayList<PassengersDetailsClass> userList30 = db.getHistory30(userId);
                li.setAdapter(new CustomListAdapter(getApplicationContext(), userList30));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    //    public  ArrayList getListData(){
//       ArrayList<PassengersDetailsClass> results = db.getPassengersDetails(userId);
//
//       // PassengersDetailsClass item1 = new PassengersDetailsClass();
////        item1.setTime("0900hrs-1300hrs");
////        item1.setTravelsName("RESHMA TOURS AND TRAVELS");
////        item1.setPrice("Paid");
////        item1.setFrom("From: Udupi");
////        item1.setTo("To: Mangalore");
////        item1.setPrice("980");
////        item1.setDuration("4 hours");
////        item1.setType("NON-AC SLEEPER");
////        item1.setDate("13-July-2023");
////        results.add(item1);
//               return results;
//    }
    public class CustomListAdapter extends BaseAdapter {
        private ArrayList<PassengersDetailsClass> listData;
        private LayoutInflater layoutInflater;

        public CustomListAdapter(Context aContext, ArrayList<PassengersDetailsClass> listData) {
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
            TextView uTime,uPassengerName,uSeatNo,uTravelName,uPaid,uFrom,uTo,uPrice,uDuration,uType,uDate;
        }
        @Override
        public View getView(int position, View v, ViewGroup vg) {
            ViewHolder holder;
            if (v == null) {
                v = layoutInflater.inflate(R.layout.history_custom_list, null);
                holder = new ViewHolder();
                holder.uTime = (TextView) v.findViewById(R.id.lstTime);
                holder.uTravelName = (TextView) v.findViewById(R.id.lstTravelsName);
                holder.uPassengerName= (TextView) v.findViewById(R.id.lsttravellersName);
                holder.uSeatNo= (TextView) v.findViewById(R.id.lstseatNo);
                holder.uPaid = (TextView) v.findViewById(R.id.lstPaid);
                holder.uFrom = (TextView) v.findViewById(R.id.lstFrom);
                holder.uTo = (TextView) v.findViewById(R.id.lstTo);
                holder.uPrice = (TextView) v.findViewById(R.id.lstPrice);
                holder.uDuration = (TextView) v.findViewById(R.id.lstDuration);
                holder.uType = (TextView) v.findViewById(R.id.lstType);
                holder.uDate = (TextView) v.findViewById(R.id.lstDate);

                v.setTag(holder);
            }
            else {
                holder = (ViewHolder) v.getTag();
            }
            holder.uTime.setText("Time : "+ listData.get(position).getTime());
            holder.uTravelName.setText("Travels : "+listData.get(position).getTravelsName());
            holder.uPassengerName.setText("Name : "+listData.get(position).getPassengerName());
            holder.uSeatNo.setText("Seat No : "+listData.get(position).getSeatNo());
            holder.uFrom.setText("From : "+listData.get(position).getFrom());
            holder.uTo.setText("To : "+listData.get(position).getTo());
           // holder.uPrice.setText(listData.get(position).getPrice());
            holder.uPrice.setText("₹"+String.valueOf(listData.get(position).getPrice()));

            holder.uDuration.setText("Duration : "+listData.get(position).getDuration()+" hours");
            holder.uType.setText("Type : "+listData.get(position).getType());
            holder.uDate.setText("Date : "+listData.get(position).getDate());

            return v;
        }
    }
}