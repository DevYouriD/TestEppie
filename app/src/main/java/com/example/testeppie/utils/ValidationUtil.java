package com.example.testeppie.utils;

import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // A regular expression pattern to match at least one digit and one special character
        // (allowed special characters [+-.!@#*_,~]) and ensure that the string is at least 6 characters long
        String regex = "^(?=.*\\d)(?=.*[+-.!@#*_,~]).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            field.setError("Password needs to be at least 6 long, contain 1 digit and 1 special character");
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
        // A regular expression pattern to match a phone number.
        String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            field.setError("Please enter a valid phone number (length 10)");
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
}
