package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class ChooseDestination extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    Spinner sourceSpinner, destinationSpinner;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    EditText dateEditText;
    Button btnSelect;
    String selectedDate="";
    DatabaseHandler db;
    ImageView profileSettings;

    boolean isDarkTheme;
    private ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_destination);
        constraintLayout=findViewById(R.id.layoutMain);
        db = new DatabaseHandler(this);
        sourceSpinner = findViewById(R.id.spSource);
        destinationSpinner = findViewById(R.id.spDestination);
        profileSettings=findViewById(R.id.profileSettings);

        ArrayList<String> arrayListSrc = new ArrayList<>();
        arrayListSrc.add("From"); // Add your new element here
        arrayListSrc.addAll(Arrays.asList(getResources().getStringArray(R.array.spinner_items)));

        ArrayAdapter<String> adapterSrc =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListSrc);
        adapterSrc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(adapterSrc);

        ArrayList<String> arrayListDes = new ArrayList<>();
        arrayListDes.add("To"); // Add your new element here
        arrayListDes.addAll(Arrays.asList(getResources().getStringArray(R.array.spinner_items)));

        ArrayAdapter<String> adapterDes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayListDes);
        adapterDes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(adapterDes);


        dateEditText =(EditText) findViewById(R.id.dateEditText);
        btnSelect=(Button) findViewById(R.id.btnSelectBus);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //Darktheme using shared pref
        SharedPreferences sharedPrefs = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        isDarkTheme = sharedPrefs.getBoolean("night", false);
        if(isDarkTheme)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        profileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                if ((sharedPreferences.getInt("userId", 0)) == -1) {

//                    Toast.makeText(getApplicationContext(),"Please Login!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ChooseDestination.this, LoginPage.class));
                }
                else {
                    startActivity(new Intent(ChooseDestination.this, settingsActivity.class));
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current background drawable
                Drawable background = btnSelect.getBackground();

                // Set the alpha value (0-255) you desire (e.g., 128 for half-opacity)
                int alphaValue = 128;

                // Modify the alpha value of the background
                background.setAlpha(alphaValue);

                // Update the background of the button
                btnSelect.setBackground(background);
                if(sourceSpinner.getSelectedItemPosition()==0)
                {
                    Snackbar snackbar;
                    Snackbar.make(constraintLayout,"Select Start Location",Snackbar.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "Select Start Location", Toast.LENGTH_LONG).show();
                }else if (destinationSpinner.getSelectedItemPosition()==0){
                    Snackbar snackbar;
                    Snackbar.make(constraintLayout,"Select Destination Location",Snackbar.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "Select Destination Location", Toast.LENGTH_LONG).show();
                }else if(dateEditText.getText().toString().isEmpty()){
                    Snackbar snackbar;
                    Snackbar.make(constraintLayout,"Select the Date",Snackbar.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "Select the Date", Toast.LENGTH_LONG).show();
                }else {
                    ArrayList<busdetails> availableBuses = db.getAvailableBuses(sourceSpinner.getSelectedItem().toString(), destinationSpinner.getSelectedItem().toString(), selectedDate);
                    if(availableBuses.isEmpty()){
                        Snackbar snackbar;
                        Snackbar.make(constraintLayout,"No Buses Available!",Snackbar.LENGTH_LONG).show();
//                        Toast.makeText(getApplicationContext(), "No Buses Available!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(ChooseDestination.this, busselection.class);
                        intent.putExtra("AvailableBuses", availableBuses);
                        intent.putExtra("sourceEditText", sourceSpinner.getSelectedItem().toString());
                        intent.putExtra("destinationEditText", destinationSpinner.getSelectedItem().toString());
                        intent.putExtra("date", selectedDate);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the EditText with the selected date.
                        calendar.set(year, monthOfYear, dayOfMonth);
                        selectedDate = dateFormat.format(calendar.getTime());
                        dateEditText.setText(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        datePickerDialog.show();
    }
}