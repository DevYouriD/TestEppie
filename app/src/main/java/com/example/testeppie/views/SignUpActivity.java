package com.example.testeppie.views;

import static com.example.testeppie.utils.ActivityStateUtil.handleSignIn;
import static com.example.testeppie.utils.ValidationUtil.validateEmail;
import static com.example.testeppie.utils.ValidationUtil.validatePassword;
import static com.example.testeppie.utils.ValidationUtil.validatePhone;
import static com.example.testeppie.utils.ValidationUtil.validateUsername;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

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
public class SignUpActivity extends AppCompatActivity {

    private EditText usernameInputField;
    private EditText emailInputField;
    private EditText phoneInputField;
    private EditText passwordInputField;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInputField = findViewById(R.id.userInputSignUp);
        emailInputField = findViewById(R.id.emailInputSignUp);
        phoneInputField = findViewById(R.id.phoneInputSignUp);
        passwordInputField = findViewById(R.id.passwordInputSignUp);
        progressBar = findViewById(R.id.progressBarSignUp);

        firebaseAuth = FirebaseUtil.getFirebaseAuth();
        database = FirebaseUtil.getFirebaseDatabase(this);
    }

    /**
     * Sign up the user after the button is clicked.
     */
    public void signupButtonClicked(View view) {
        String usernameInput = usernameInputField.getText().toString().trim();
        String emailInput = emailInputField.getText().toString().trim();
        String phoneInput = phoneInputField.getText().toString().trim();
        String passwordInput = passwordInputField.getText().toString().trim();

        if (validateEmail(emailInput, emailInputField) &&
                validatePassword(passwordInput, passwordInputField) &&
                validateUsername(usernameInput, usernameInputField) &&
                validatePhone(phoneInput, phoneInputField)) {

            progressBar.setVisibility(View.VISIBLE);

            firebaseAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            User user = new User(usernameInput, passwordInput, phoneInput, emailInput);

                            database.getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            handleSignIn(this, progressBar, View.GONE, "User created successfully");

                                            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                            finish();
                                        } else {
                                            handleSignIn(this, progressBar, View.GONE, "Failed to create user");
                                        }
                                    });
                        } else {
                            handleSignIn(this, progressBar, View.GONE, "Failed to create user");
                        }
                    });
        }
    }
}