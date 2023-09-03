package com.example.testeppie;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testeppie.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPhone;
    EditText editTextPassword;

    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUserName = findViewById(R.id.newUserInput);
        editTextEmail = findViewById(R.id.newEmailInput);
        editTextPhone = findViewById(R.id.newPhoneInput);
        editTextPassword = findViewById(R.id.newPasswordInput);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void signupButtonClicked(View view) {
        String txtUserName = editTextUserName.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();
        String txtPhone = editTextPhone.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();

        // Some simple validation for the fields
        if (txtUserName.isEmpty()) {
            editTextUserName.setError("Please enter UserName");
            editTextUserName.requestFocus();
        }

        if (txtEmail.isEmpty()) {
            editTextEmail.setError("Please enter valid email address");
            editTextEmail.requestFocus();
        }

        if (txtPassword.isEmpty() || txtPassword.length() < 6) {
            editTextPassword.setError("Please enter password containing at least six characters");
            editTextPassword.requestFocus();
        }

        if (txtPhone.isEmpty()) {
            editTextPhone.setError("Please enter phone number");
            editTextPhone.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(txtUserName, txtPassword, txtPhone, txtEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignUpActivity.this, "User created successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            } else {
                                                Toast.makeText(SignUpActivity.this, "Failed to create user", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });


                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to create user", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}