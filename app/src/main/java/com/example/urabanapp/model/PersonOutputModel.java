package com.example.urabanapp.model;

public class PersonOutputModel {

    public boolean status;
    public String message;
    public PersonModel people;

    public PersonOutputModel(boolean status, String message, PersonModel people) {
        this.status = status;
        this.message = message;
        this.people = people;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonModel getPeople() {
        return people;
    }

    public void setPeople(PersonModel people) {
        this.people = people;
    }
}
