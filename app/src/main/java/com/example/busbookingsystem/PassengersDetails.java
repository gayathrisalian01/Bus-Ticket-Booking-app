package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class PassengersDetails extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    EditText editTextName;
    EditText editTextSeatNo;
    EditText editTextNoOfSeat;
    EditText editTextContactNo;
    EditText editTextEmail;
    Button buttonNext;
    EditText editTextContact;
    EditText editTextDate;
    TextView textViewSource,textViewDestination, textDate, textTime, textDuration;
    Calendar calendar = Calendar.getInstance();

    String name,contact;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers_details);
        imageView = findViewById(R.id.imageView5);
        textTime = findViewById(R.id.textView4);
        editTextName = findViewById(R.id.editTextText2);
        editTextSeatNo = findViewById(R.id.editTextText3);
        editTextNoOfSeat = findViewById(R.id.editTextText4);
        editTextContactNo = findViewById(R.id.editTextText5);
        editTextEmail = findViewById(R.id.editTextText6);
        buttonNext = findViewById(R.id.buttonnext);
        editTextContact = findViewById(R.id.editTextText5);


        textViewSource=findViewById(R.id.textSource);
        textViewDestination=findViewById(R.id.textDestination);

        back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PassengersDetails.this.finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        int userId=sharedPreferences.getInt("userId", 0);
        String useremail=sharedPreferences.getString("useremail", "");

        Intent intent=getIntent();
        int seatNo=intent.getIntExtra("seatNo",0);
        int busId=intent.getIntExtra("busId",0);
        String from = intent.getStringExtra("source");
        String to = intent.getStringExtra("destination");
        String time = intent.getStringExtra("Time");
        String busName = intent.getStringExtra("BusName");
        String price = intent.getStringExtra("Price");
        String date = intent.getStringExtra("Date");
        String duration = intent.getStringExtra("Duration");
        String type = intent.getStringExtra("Type");

        //bhbchskcbes
        textViewSource.setText(from);
        textViewDestination.setText(to);



        textTime.setText(date+" | "+time+"\n"+duration);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(ValidateFields()){
                 GoTONextPage();
             }
            }

            private void GoTONextPage() {

                //name
                name= editTextName.getText().toString();
                //contact no
                contact= editTextContactNo.getText().toString();

                Intent intent=new Intent(PassengersDetails.this, PaymentOptions.class);
                intent.putExtra("busId",busId);
                intent.putExtra("userId",userId);
                intent.putExtra("seatNo",seatNo);
                intent.putExtra("name",name);
                intent.putExtra("contact",contact);

                intent.putExtra("time",time);
                intent.putExtra("duration",duration);
                intent.putExtra("destination",to);
                intent.putExtra("source",from);
                intent.putExtra("Date",date);
                intent.putExtra("Price",price);
                startActivity(intent);
            }

            private boolean ValidateFields() {
                //name
                name= editTextName.getText().toString();
                //contact no
                contact= editTextContactNo.getText().toString();
                String regex = "^[6789]\\d{0,9}$";
                String regexName = "^[A-Za-z][A-Za-z ]*$";
                if(!name.matches(regexName))
                {
                    editTextName.setError("Please Enter Valid Name");
                    return false;
                }
                if(!contact.matches(regex))
                {
                    editTextContact.setError("Please Enter Proper Contact Number");
                    return false;
                }
                return true;
            }
        });

        editTextSeatNo.setText(String.valueOf(seatNo+1)); // Using String.valueOf() for conversion
        //no of seats constant one
        editTextNoOfSeat = findViewById(R.id.editTextText4);
        // Set the text for the EditText
        String seatNumber = "1"; // Replace with the actual seat number
        editTextNoOfSeat.setText(seatNumber);


        //email
        EditText emailEditText = findViewById(R.id.editTextText6);
        emailEditText.setText(useremail);




        /*editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        Button buttonNext = findViewById(R.id.buttonnext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                String contact = editTextContact.getText().toString();
                String selectedDate = editTextDate.getText().toString();

                if (!name.isEmpty() && !contact.isEmpty() && !selectedDate.isEmpty()) {
                    textView.setText("Welcome, " + name + "! Seat No: Your seat number here. Contact: " + contact + " Date: " + selectedDate);
                } else {
                    textView.setText("Please enter your name, contact number, and date.");
                }
            }
        });
    }


    private void showDatePicker() {
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++; // Adjust the month (0-based to 1-based)
                String date = month + "-" + dayOfMonth + "-" + year;
                editTextDate.setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}*/

    }

}