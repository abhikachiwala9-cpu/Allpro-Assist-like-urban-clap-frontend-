package com.example.urabanapp.model;

import java.io.Serializable;

public class PersonModel implements Serializable {

    public String username;
    public String email;
    public String password;
    public String mobileno;
    public String address;
    public String id;

    public PersonModel(String username, String email, String password, String mobileno, String address, String id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobileno = mobileno;
        this.address = address;
        this.id = id;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
