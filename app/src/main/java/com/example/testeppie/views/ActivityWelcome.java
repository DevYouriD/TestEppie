package com.example.testeppie.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeppie.R;
import com.example.testeppie.utils.SecretsManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Properties;

public class ActivityWelcome extends AppCompatActivity {

    EditText editTextPassword;
    EditText editTextEmail;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //TODO change the ids in al the views to be the same (now we have things like `newEmail` and `emailInput` use one convention for all.
        editTextEmail = findViewById(R.id.emailInput);
        editTextPassword = findViewById(R.id.passwordInput);
        //TODO change name of progressbar2 (possible to add the view after the name `progressbarWelcome` or some sort)
        progressBar = findViewById(R.id.progressBar2);

        //TODO we need to get this one one place, i'm dubplicating code now :(.
        firebaseAuth = FirebaseAuth.getInstance();


        // Get the database connection URL (only for `us-central1` you don't need an url argument)
        Properties secretProperties = SecretsManager.loadSecretProperties(getApplicationContext());
        String dbUrl = secretProperties.getProperty("fire_base_url");

        // Setup base firebase app
        FirebaseApp.initializeApp(this);

        // Test connection to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance(dbUrl);
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World! - connected");
    }

    public void onButtonSignUpClicked(View view) {
        startActivity(new Intent(ActivityWelcome.this, SignUpActivity.class));
    }

    public void onButtonSignInClicked(View view) {
        String txtEmail = editTextEmail.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
            editTextEmail.setError("Please Enter a Valid Email");
            editTextEmail.requestFocus();
        }

        if (txtPassword.isEmpty() || txtPassword.length() < 6) {
            editTextPassword.setError("Please enter password containing at least six characters");
            editTextPassword.requestFocus();
        }


        //TODO check if empty earlier on (checking it now twice.., not optimal)
        if (!txtEmail.isEmpty() && !txtPassword.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.signInWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(ActivityWelcome.this, "Successfully signed in", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);

                    startActivity(new Intent(ActivityWelcome.this, MainActivity.class));
                } else {
                    Toast.makeText(ActivityWelcome.this, "Failed to sign in", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    //TODO add forgot password
}