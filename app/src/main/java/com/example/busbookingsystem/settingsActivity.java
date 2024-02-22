package com.example.busbookingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class settingsActivity extends AppCompatActivity {
    ImageView settingsImageView;
    TextView settingsTextView;
    TextView edittextTextview;
    ImageView editarrowImageView;
    TextView busTextView;
    ImageView busarrowpImageView;

    TextView deleteHistoryTextView;

    LinearLayout settingLayout;

    ImageView arrowDeleteImageView;

    ImageView logoutImageView;
    TextView logoutTextView;
    TextView busarrowpTextView;
    TextView nameperson;

    LinearLayout editLayout, historyLayout, deleteLayout, logOutLayout;

    //    ToggleButton customToggleButton;
    Switch themeSwitch;
    boolean nightMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DatabaseHandler(this);
        // Find views by their IDs
        settingsImageView = findViewById(R.id.imagesetting);
        settingsTextView = findViewById(R.id.textsetting);

        nameperson=findViewById(R.id.nameperson);

        edittextTextview = findViewById(R.id.edittext);
        editarrowImageView = findViewById(R.id.editarrow);

        busarrowpTextView = findViewById(R.id.busp);
        busarrowpImageView = findViewById(R.id.busarrowp);

        deleteHistoryTextView = findViewById(R.id.delhis);
        arrowDeleteImageView = findViewById(R.id.arrowdel);

        logoutImageView = findViewById(R.id.log);
        logoutTextView = findViewById(R.id.textlog);
        //ToggleButton customToggleButton = findViewById(R.id.customToggleButton);

        editLayout= findViewById(R.id.editLayout);
        historyLayout= findViewById(R.id.historyLayout);
        deleteLayout= findViewById(R.id.deleteLayout);
        logOutLayout=findViewById(R.id.logOutLayout);
        settingLayout=findViewById(R.id.settingLayout);

        //Dark/Light mode
        themeSwitch = findViewById(R.id.themeSwitch);
        sharedPreferences=getSharedPreferences("MODE",Context.MODE_PRIVATE);
        nightMODE=sharedPreferences.getBoolean("night",false);//Light mode is default mode
        if(nightMODE)
        {
            themeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        themeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMODE)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("night",false);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor=sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();
            }
        });
//        themeSwitch.setChecked(
//                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
//        );
//        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//
//                // Save the selected theme to SharedPreferences
//                saveThemePreference(isChecked);
//            }
//
//        });
        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        nameperson.setText("Hey! "+sharedPreferences.getString("username",""));

        // Set a click listener for the ImageView
        settingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(settingsActivity.this, ChooseDestination.class);
                startActivity(in);
            }
        });

        // You can also set a click listener for the TextView if needed
        settingsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Handle TextView click event (e.g., go to settings)
            }
        });


        edittextTextview.setText("Edit Your Profile");
        edittextTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(settingsActivity.this, UpdateProfileActivity.class));

            }
        });
        editarrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(settingsActivity.this, UpdateProfileActivity.class));
            }
        });
        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(settingsActivity.this, UpdateProfileActivity.class));
            }
        });

        busarrowpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(settingsActivity.this, historyActivity.class));
            }
        });

        busarrowpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(settingsActivity.this, historyActivity.class));

            }
        });
        historyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(settingsActivity.this, historyActivity.class));
            }
        });
        deleteHistoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                db.deletePassengerHistory();
//                Snackbar snackbar;
//                Snackbar.make(settingLayout,"Travel History Cleared",Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Travel History Cleared", Toast.LENGTH_SHORT).show();
                showDeleteDialog();
            }
        });
        deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar snackbar;
//                Snackbar.make(settingLayout,"Travel History Cleared",Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Travel History Cleared", Toast.LENGTH_SHORT).show();
                showDeleteDialog();
            }
        });

        // Set an OnClickListener for the arrowDeleteImageView if needed
        arrowDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                db.deletePassengerHistory();
//                Snackbar snackbar;
//                Snackbar.make(settingLayout,"Travel History Cleared",Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"Travel History Cleared", Toast.LENGTH_SHORT).show();
                showDeleteDialog();
            }
        });


        logOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog();
            }
        });

        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });

        logoutImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });
    }

    private void saveThemePreference(boolean isDarkTheme) {
        // Save the selected theme preference to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("darkTheme", isDarkTheme);
        editor.apply();

    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Perform the log out action
                        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("userId", -1);
                        editor.putString("username", "");
                        editor.putString("useremail", "");
                        editor.putString("contact", "");
                        editor.apply();
                        startActivity(new Intent(settingsActivity.this, ChooseDestination.class));
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

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure that you want to clear your history ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deletePassengerHistory();
                        Snackbar snackbar;
                        Snackbar.make(settingLayout,"Travel History Cleared",Snackbar.LENGTH_SHORT).show();
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

    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(settingsActivity.this, ChooseDestination.class);
        startActivity(in);
    }
      //customToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

    //{
    //  public void onCheckedChanged (CompoundButton customToggleButton,boolean isChecked ){
    //if (isChecked) {
    // Enable dark mode
    //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    // } else {
    // Disable dark mode (use light mode)
    //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    //}
    //recreate();
    //}

    }
