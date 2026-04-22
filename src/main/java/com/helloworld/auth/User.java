package com.helloworld.auth;

public class User {
    String username;
    String password;
    int failedAttempts;
    boolean isLocked;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.failedAttempts = 0;
        this.isLocked = false;
    }
}
