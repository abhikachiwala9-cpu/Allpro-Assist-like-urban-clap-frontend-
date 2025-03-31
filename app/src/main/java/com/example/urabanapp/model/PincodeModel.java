package com.example.urabanapp.model;

public class PincodeModel {

    public String pincode,locality,id;

    public PincodeModel(String pincode, String locality, String id) {
        this.pincode = pincode;
        this.locality = locality;
        this.id = id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
