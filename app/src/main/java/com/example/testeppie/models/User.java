package com.example.testeppie.models;

public class User {

    public String userName;
    public String email;
    public String phone;
    public String password;

    // Simple constructor
    public User() {

    }

    public User(String newUserName, String newPassword, String newPhone, String newEmail) {
        this.userName = newUserName;
        this.email = newEmail;
        this.phone = newPhone;
        this.password = newPassword;
    }
}
