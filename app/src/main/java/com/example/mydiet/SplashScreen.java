package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private long  splashTIme = 3000L;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotMainActivity();

            }
        },splashTIme);
    }

    private void gotMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
