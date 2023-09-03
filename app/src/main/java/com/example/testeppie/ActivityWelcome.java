package com.example.testeppie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeppie.utils.SecretsManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Properties;

public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Get the database connection URL
        Properties secretProperties = SecretsManager.loadSecretProperties(getApplicationContext());
        String dbUrl = secretProperties.getProperty("fire_base_url");

        // Test connection to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(dbUrl);
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