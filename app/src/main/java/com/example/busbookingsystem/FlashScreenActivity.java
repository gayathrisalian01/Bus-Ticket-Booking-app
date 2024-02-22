package com.example.busbookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class FlashScreenActivity extends AppCompatActivity {
    ImageView gifImageView;
    private static final int SPLASH_TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FlashScreenActivity.this, ChooseDestination.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}