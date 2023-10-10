package com.example.testeppie.views;

import static com.example.testeppie.utils.ActivityStateUtil.handleSignIn;
import static com.example.testeppie.utils.ValidationUtil.validateEmail;
import static com.example.testeppie.utils.ValidationUtil.validatePassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testeppie.R;
import com.example.testeppie.utils.FirebaseUtil;

/**
 * The welcome view.
 */
public class SignInActivity extends AppCompatActivity {

    private EditText passwordInputField;
    private EditText emailInputField;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailInputField = findViewById(R.id.emailInputSignIn);
        passwordInputField = findViewById(R.id.passwordInputSignIn);
        progressBar = findViewById(R.id.progressBarSignIn);
    }

    /**
     * Redirect the user to the sign up page.
     */
    public void onButtonSignUpClicked(View view) {
        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

    /**
     * Sign in after the button is clicked.
     * <p>User gets redirected after a successful login.</p>
     */
    public void onButtonSignInClicked(View view) {
        String emailInput = emailInputField.getText().toString().trim();
        String passwordInput = passwordInputField.getText().toString().trim();

        /* If the email validation fails the password validation won't be called and the user only
         * sees a message at the email input field (even if the password is also incorrect).
         */
        if (validateEmail(emailInput, emailInputField)
                && validatePassword(passwordInput, passwordInputField)) {
            progressBar.setVisibility(View.VISIBLE);

            FirebaseUtil.getFirebaseAuth()
                    .signInWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            handleSignIn(this, progressBar, View.GONE, "Successfully signed in");

                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        } else {
                            handleSignIn(this, progressBar, View.GONE, "Failed to sign in");
                        }
                    });
        }
    }

}