package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashScreen.this,MainActivity.class));
//                finish();
                Intent mainIntent = new Intent(splash.this, MainActivity.class);
//                SplashScreen.this.startActivity(mainIntent);
//
                startActivity(mainIntent);
                finish();
            }
        },5000);
    }
    public void onBackPressed(){

    }
}

