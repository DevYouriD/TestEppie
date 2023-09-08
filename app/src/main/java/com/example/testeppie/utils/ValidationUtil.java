package com.example.testeppie.utils;

import android.util.Patterns;
import android.widget.EditText;

/**
 * Validation util.
 */
public class ValidationUtil {

    /**
     * The constructor.
     */
    private ValidationUtil() {
        // Do nothing.
    }

    /**
     * Validate the email address.
     *
     * @param email The given email.
     * @param field The field where the email is presented.
     * @return True if the email meets the requirements.
     */
    public static boolean validateEmail(String email, EditText field) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            field.setError("Please Enter a Valid Email");
            field.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Validate the password.
     *
     * @param password The given password.
     * @param field    The field where the password is presented.
     * @return True if the password meets the requirements.
     */
    public static boolean validatePassword(String password, EditText field) {
        if (password.isEmpty() || password.length() < 6) {
            field.setError("Please enter password containing at least six characters");
            field.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Validate the username.
     *
     * @param username The given username.
     * @param field    The field where the username is presented.
     * @return True if the username meets the requirements.
     */
    public static boolean validateUsername(String username, EditText field) {
        if (username.isEmpty()) {
            field.setError("Please enter UserName");
            field.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Validate the phone number.
     *
     * @param phone The given phone number.
     * @param field The field where the phone number is presented.
     * @return True if the phone number meets the requirements.
     */
    public static boolean validatePhone(String phone, EditText field) {
        if (phone.isEmpty()) {
            field.setError("Please enter phone number");
            field.requestFocus();
            return false;
        }
        return true;
    }
}
