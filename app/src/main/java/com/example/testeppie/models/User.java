package com.example.testeppie.models;


/**
 * The domain object used for user(s).
 */
public class User {

    public String userName;
    public String email;
    public String phone;
    public String password;

    /**
     * The constructor.
     *
     * @param userName The user's username.
     * @param email    The user's email address.
     * @param phone    The user's phone number.
     * @param password The user's password.
     */
    public User(String userName, String email, String phone, String password) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

}

