package com.example.testeppie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //TODO add the url to a config file (local so i don't push it to github) 
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World! - connected");
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