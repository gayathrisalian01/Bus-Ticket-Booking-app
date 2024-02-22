package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    EditText ETname,ETcontact,ETemail,ETpassword;
    Button btnRegister;
    DatabaseHandler db;
    ImageView passview;
    RelativeLayout registerLayout;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ETname=findViewById(R.id.usernameEditText);
        ETcontact=findViewById(R.id.phoneEditText);
        ETemail=findViewById(R.id.emailEditText);
        ETpassword=findViewById(R.id.passwordEditText);
        btnRegister=findViewById(R.id.registerButton);
        passview=findViewById(R.id.passview);
        registerLayout=findViewById(R.id.registerLayout);
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields()){
                    StoreUserDetails();
                }
            }
        });
    }

    public boolean validateFields() {
        String name=ETname.getText().toString();
        String contact=ETcontact.getText().toString();
        String email=ETemail.getText().toString();
        String password=ETpassword.getText().toString();

        String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@(gmail\\.com)$";

        String regex = "^[6789]\\d{0,9}$";

        String regexName = "^[A-Za-z][A-Za-z ]*$";

        if(!name.matches(regexName))
        {
            ETname.setError("Please Enter Valid Name");
            return false;
        }
        if(!contact.matches(regex))
        {
            ETcontact.setError("Please Enter Proper Contact Number");
            return false;
        }
        if(email.isEmpty() || !email.matches(emailRegex))
        {
            ETemail.setError("Invalid Email Format");

        }
        if (password.length() < 6 || !password.matches(".*\\d.*") || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*")) {
            ETpassword.setError("Password must contain at least 6 characters, including uppercase, lowercase, and a number");
            return false;
        }
        return true;
    }
    public void StoreUserDetails(){
        String name=ETname.getText().toString();
        String contact=ETcontact.getText().toString();
        String email=ETemail.getText().toString();
        String password=ETpassword.getText().toString();
        Boolean checkuser=db.checkusername(email);
        if(checkuser==false)
        {
            BusBookingConstructor busBookingCustructor=new BusBookingConstructor(name,contact,email,password);
            Boolean isInserted=db.insertuserdata(busBookingCustructor);
            if(isInserted==true)
            {
                Toast.makeText(RegisterActivity.this, "Registration Sucessfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginPage.class));
            }
            else
            {
                Snackbar snackbar;
                Snackbar.make(registerLayout,"Registration Failed",Snackbar.LENGTH_LONG).show();
//                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Snackbar snackbar;
            Snackbar.make(registerLayout,"User Already Exists Please Log in",Snackbar.LENGTH_LONG).show();
//            Toast.makeText(RegisterActivity.this, "User Already Exists Please Log in", Toast.LENGTH_SHORT).show();
        }
    }
}