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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginPage extends AppCompatActivity {
    TextView Tvregister;
    RelativeLayout loginLayout;
    EditText ETusername,ETpassword;
    Button btnLogin;
    ImageView passview;
    private boolean isPasswordVisible = false;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Tvregister=findViewById(R.id.TvReg);

        ETusername=findViewById(R.id.usernameEditText);
        ETpassword=findViewById(R.id.passwordEditText);

        btnLogin=findViewById(R.id.loginButton);
        passview=findViewById(R.id.passview);

        db=new DatabaseHandler(this);

        loginLayout=findViewById(R.id.loginLayout);
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

        Tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ValidateFields())
                {
                    goTOMainPage();
                }

            }
        });


    }
    public Boolean ValidateFields(){
        String username=ETusername.getText().toString();
        String password=ETpassword.getText().toString();

        if(username.isEmpty())
        {
            ETusername.setError("Please Enter Username");
            return false;
        }
        if(password.isEmpty())
        {
            ETpassword.setError("Please Enter Password");
            return false;
        }
        return true;
    }
    public void onBackPressed() {
        startActivity(new Intent(LoginPage.this, ChooseDestination.class));
    }
    public void goTOMainPage(){
        String username=ETusername.getText().toString();
        String password=ETpassword.getText().toString();
        //shared pref using DatabaseHandler
        BusBookingConstructor busBookingCustructor=db.checkUsernamePassword(username,password);


//        int id=busBookingCustructor.getuid();
//         String email=busBookingCustructor.get_email();

        //user id  for user update page
//        SharedPreferences sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("userid",username);
//        editor.apply();

//        BusBookingConstructor checkUserPassword=db.checkUsernamePassword(username,password);
        if(busBookingCustructor==null)
        {
            Snackbar snackbar;
            Snackbar.make(loginLayout,"Please check Username and Password",Snackbar.LENGTH_LONG).show();
//            Toast.makeText(LoginPage.this, "Please check Username and Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //Intent for main page
//            Toast.makeText(LoginPage.this, "Login Successfull" , Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(LoginPage.this, ChooseDestination.class);
            startActivity(intent);

        }

    }
}