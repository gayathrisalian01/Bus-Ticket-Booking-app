package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentSuccessful extends AppCompatActivity implements View.OnClickListener {
    ConstraintLayout myLayout;

    TextView txtFrom,txtTo,txtDate,txtPrice,txtTransaction;
    String destination,source,Date,Price, name;
    int number=267600008;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);
       // Toast.makeText(getApplicationContext(),"Booking Confirmed!", Toast.LENGTH_LONG).show();
        txtFrom=findViewById(R.id.txtFrom);
        txtTo=findViewById(R.id.txtTo);
        txtDate=findViewById(R.id.txtDate);
        txtPrice=findViewById(R.id.txtPrice);
        txtTransaction=findViewById(R.id.txtTransaction);
        myLayout = findViewById(R.id.layoutConfirmation);

        myLayout.setOnClickListener(this);

        Intent intent=getIntent();
        destination=intent.getStringExtra("destination");
        source=intent.getStringExtra("source");
        Date=intent.getStringExtra("Date");
        Price = intent.getStringExtra("Price");
        name=intent.getStringExtra("Name");

        number=number+1;
        txtFrom.setText(source);
        txtTo.setText(destination);
        txtDate.setText(Date);
        txtPrice.setText(Price);
        txtTransaction.setText("Transaction Number : "+String.valueOf(number));


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PaymentSuccessful.this, ChooseDestination.class));
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(PaymentSuccessful.this, ChooseDestination.class));
    }
}