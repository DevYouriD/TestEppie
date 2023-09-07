package com.example.testeppie.views;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeppie.R;
import com.example.testeppie.config.FirebaseConfiguration;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseConfiguration.initializeFirebaseApp();

        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(ActivitySplash.this, ActivityWelcome.class));

            finish();
        }, 3000);
    }
}