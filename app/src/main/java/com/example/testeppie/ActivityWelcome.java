package com.example.testeppie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onButtonSignUpClicked(View view) {
        // DO nothing
        // TODO create logic
    }

    public void onButtonSignInClicked(View view) {
        // TODO change and add logic
        startActivity(new Intent(ActivityWelcome.this, MainActivity.class));
    }
}