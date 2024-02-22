package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateProfileActivity extends AppCompatActivity {
    EditText ETusername,ETcontact,ETpassword;
    Button btnUpdate;
    ImageView passview;
    DatabaseHandler db;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ETusername=findViewById(R.id.usernameEditText);
        ETcontact=findViewById(R.id.phoneEditText);
        ETpassword=findViewById(R.id.passwordEditText);

        btnUpdate=findViewById(R.id.updateButton);
        passview=findViewById(R.id.passview);

        db=new DatabaseHandler(this);

        //to show/hide password
        passview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    // Hide the password
                    ETpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passview.setImageResource(R.drawable.password_visibility_off);
                } else {
                    // Show the password
                    ETpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passview.setImageResource(R.drawable.password_visibility_on);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        //To set the user details prior to update
        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        int shareduserid = sharedPreferences.getInt("userId",0);

        String userdata[]=db.getUserData(shareduserid);

        if (userdata != null) {
            ETusername.setText(userdata[0]);
            ETcontact.setText(userdata[1]);
            ETpassword.setText(userdata[2]);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidateFields())
                {
                    updateDetails();
                }

            }
        });
    }
    public Boolean ValidateFields(){
        String username=ETusername.getText().toString();
        String password=ETpassword.getText().toString();
        String contact=ETcontact.getText().toString();

        if(username.isEmpty())
        {
            ETusername.setError("Please Enter Username");
            return false;
        }
        if(contact.length() != 10)
        {
            ETcontact.setError("Please Enter Proper Contact Number");
            return false;
        }
        if (password.length() < 6 || !password.matches(".*\\d.*") || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
            ETpassword.setError("Password must contain at least 6 characters, including uppercase, lowercase, and a number");
            return false;
        }
        return true;
    }
    public void updateDetails(){
        //Email address for updating details
        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        int sharedUserId = sharedPreferences.getInt("userId",0);

        String email = sharedPreferences.getString("useremail","");

        String username=ETusername.getText().toString();
        String password=ETpassword.getText().toString();
        String contact=ETcontact.getText().toString();

        Boolean isUpdated=db.updateUserdata(sharedUserId,username,contact,email,password);
        if(isUpdated==true)
        {
            Toast.makeText(UpdateProfileActivity.this, "Your Profile Updated", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UpdateProfileActivity.this, LoginPage.class));
        }
        else
        {
            Toast.makeText(UpdateProfileActivity.this, "Failed to Update Your Profile", Toast.LENGTH_SHORT).show();
        }
    }
}