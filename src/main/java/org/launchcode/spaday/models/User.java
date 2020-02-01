package org.launchcode.spaday.models;

import java.time.LocalDateTime;

/**
 * Created by kamdem
 */
public class User {
    private int id;
    private static int nextId = 1;
    private String username;
    private String email;
    private String  password;
    private LocalDateTime date;
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = nextId++;
        this.date = LocalDateTime.now(); // Now
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
