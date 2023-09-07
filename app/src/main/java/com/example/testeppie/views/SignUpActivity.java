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
import com.example.testeppie.models.User;
import com.example.testeppie.utils.FirebaseUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


/**
 * The view for registering new users.
 */
//TODO add javadoc and cleanup methodes
public class SignUpActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUserName = findViewById(R.id.newUserInput);
        editTextEmail = findViewById(R.id.newEmailInput);
        editTextPhone = findViewById(R.id.newPhoneInput);
        editTextPassword = findViewById(R.id.newPasswordInput);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseUtil.getFirebaseAuth();
        database = FirebaseUtil.getFirebaseDatabase(this);
    }

    //TODO fix code quality, complexity of 17 needs to be lower as 15
    public void signupButtonClicked(View view) {
        String txtUserName = editTextUserName.getText().toString().trim();
        String txtEmail = editTextEmail.getText().toString().trim();
        String txtPhone = editTextPhone.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();

        // Some simple validation for the fields
        //TODO make simple validation methode
        if (txtUserName.isEmpty()) {
            editTextUserName.setError("Please enter UserName");
            editTextUserName.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
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


        if (!txtEmail.isEmpty() && !txtPassword.isEmpty() && !txtPhone.isEmpty() && !txtUserName.isEmpty()) {
            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            User user = new User(txtUserName, txtPassword, txtPhone, txtEmail);

                            database.getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "User created successfully", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            startActivity(new Intent(SignUpActivity.this, ActivityWelcome.class));
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Failed to create user", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to create user", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }
}