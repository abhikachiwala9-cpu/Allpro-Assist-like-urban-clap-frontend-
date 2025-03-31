package com.example.urabanapp.utilites;

public class Validation {

    public static boolean isValidEmail(String email){
        String emailPattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailPattern);
    }

    public static boolean isValidPass(String password){
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return  password.matches(passwordPattern);
    }

    public static boolean isValidContactNumber(String contactNumber){
        String contactNumberPattern = "^\\d{10}$";
        return contactNumber.matches(contactNumberPattern);
    }
}
