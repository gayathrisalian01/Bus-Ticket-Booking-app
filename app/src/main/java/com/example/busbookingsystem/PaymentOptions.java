package com.example.busbookingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentOptions extends AppCompatActivity implements View.OnClickListener {
    int seatNo;
    int busId;
    int userId;
    String name, destination, source, Date, Price, Time, Duration, username;
    String contact;

    TextView TVSource, TVDestination, TVDateAndTime, TVDuration, TVPrice;
    NotificationManagerCompat notifManager;
    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        TextView amazonPayTextView = findViewById(R.id.textView6);
        TextView paytmTextView = findViewById(R.id.textView7);
        TextView creditDebitTextView = findViewById(R.id.textView9);
        TextView googlePayTextView = findViewById(R.id.textView11);
        TextView phonePayTextView = findViewById(R.id.textViewPhonepay);
        TextView netBankingTextView = findViewById(R.id.textView10);


        amazonPayTextView.setOnClickListener(this);
        googlePayTextView.setOnClickListener(this);
        paytmTextView.setOnClickListener(this);
        creditDebitTextView.setOnClickListener(this);
        phonePayTextView.setOnClickListener(this);
        netBankingTextView.setOnClickListener(this);

        Intent intent = getIntent();
        seatNo = intent.getIntExtra("seatNo", 0);
        busId = intent.getIntExtra("busId", 0);
        userId = intent.getIntExtra("userId", 0);
        name = intent.getStringExtra("name");
        contact = intent.getStringExtra("contact");

        destination = intent.getStringExtra("destination");
        source = intent.getStringExtra("source");
        Date = intent.getStringExtra("Date");
        Price = intent.getStringExtra("Price");

        Time = intent.getStringExtra("time");
        Duration = intent.getStringExtra("duration");

        TVSource = findViewById(R.id.textViewSource);
        TVDestination = findViewById(R.id.textViewDestination);
        TVDateAndTime = findViewById(R.id.textViewDateAndTime);
        TVDuration = findViewById(R.id.textViewDuration);
        TVPrice = findViewById(R.id.textViewPrice);

        TVSource.setText(source);
        TVDestination.setText(destination);
        TVDateAndTime.setText(Date + " | " + Time);
        TVPrice.setText(Price);
        TVDuration.setText(Duration);

        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        createNotificationChannel();
        builder = new NotificationCompat.Builder(this, "notifyMsg")
                .setSmallIcon(R.drawable.busicon_notification)
                .setContentTitle("GOBUS-BOOKING CONFIRMED")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Dear " + username + "!\nYour booking for "+ name  +" from " + source + " to " + destination + " is confirmed \nTravel Date : " + Date))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        notifManager = NotificationManagerCompat.from(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Payment Successfull!", Toast.LENGTH_LONG).show();

        notifManager.notify(100, builder.build());

////        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "notifyMsg")
//                    .setSmallIcon(R.drawable.baseline_email_24)
//                    .setContentTitle("GOBUS-BOOKING CONFIRMED")
//                    .setContentText("Dear " + username + "! \n Your booking from " + source + " to " + destination + " is confirmed for " + Date + " in the name of " + name)
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                    .setCategory(NotificationCompat.CATEGORY_MESSAGE);
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
//            notificationManagerCompat.notify(100, builder.build());
////        }else {
//            // You don't have the permission, request it from the user.
//
////        }

        DatabaseHandler db = new DatabaseHandler(this);
        db.insertPassengerDetails(busId, userId, name, contact, seatNo);
        db.bookedBusDetails(busId, seatNo);
        Intent intent = new Intent(PaymentOptions.this, PaymentSuccessful.class);
        intent.putExtra("destination", destination);
        intent.putExtra("source", source);
        intent.putExtra("Date", Date);
        intent.putExtra("Price", Price);
        intent.putExtra("Name", name);
        startActivity(intent);
    }


//    private void createNotificationChannel () {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "demo_channel";
//            String des = "This is for demonstration";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel("my001", name, importance);
//            channel.setDescription(des);
//            NotificationManager notifManager = getSystemService(NotificationManager.class);
//            notifManager.createNotificationChannel(channel);
//        }
//    }

    public void onBackPressed() {
        showBackConfirmationDialog();
    }
    private void showBackConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are few steps away from booking your ticket. Are you sure you want to cancel your booking ? ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Perform the log out action
                        startActivity(new Intent(PaymentOptions.this, ChooseDestination.class));
//                        Toast.makeText(getApplicationContext(),"userId"+String.valueOf(sharedPreferences.getInt("userId",0)),Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the log out, do nothing
                    }
                });
        // Create and show the AlertDialog
        builder.create().show();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notifyMsg", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}