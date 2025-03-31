package com.example.urabanapp.model;

import java.io.Serializable;

public class ServicesModel implements Serializable {
    public String sr_name;
    public String sr_pic1;
    public String sr_pic2;
    public String sr_pic3;
    public String sr_video;
    public String sr_duration;
    public String gender;
    public String description;
    public String sr_price;
    public String discount;
    public String sub_id;
    public String id;

    public ServicesModel(String sr_name, String sr_pic1, String sr_pic2, String sr_pic3, String sr_video, String sr_duration, String gender, String description, String sr_price, String discount, String sub_id, String id) {
        this.sr_name = sr_name;
        this.sr_pic1 = sr_pic1;
        this.sr_pic2 = sr_pic2;
        this.sr_pic3 = sr_pic3;
        this.sr_video = sr_video;
        this.sr_duration = sr_duration;
        this.gender = gender;
        this.description = description;
        this.sr_price = sr_price;
        this.discount = discount;
        this.sub_id = sub_id;
        this.id = id;
    }

    public String getSr_name() {
        return sr_name;
    }

    public void setSr_name(String sr_name) {
        this.sr_name = sr_name;
    }

    public String getSr_pic1() {
        return sr_pic1;
    }

    public void setSr_pic1(String sr_pic1) {
        this.sr_pic1 = sr_pic1;
    }

    public String getSr_pic2() {
        return sr_pic2;
    }

    public void setSr_pic2(String sr_pic2) {
        this.sr_pic2 = sr_pic2;
    }

    public String getSr_pic3() {
        return sr_pic3;
    }

    public void setSr_pic3(String sr_pic3) {
        this.sr_pic3 = sr_pic3;
    }

    public String getSr_video() {
        return sr_video;
    }

    public void setSr_video(String sr_video) {
        this.sr_video = sr_video;
    }

    public String getSr_duration() {
        return sr_duration;
    }

    public void setSr_duration(String sr_duration) {
        this.sr_duration = sr_duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSr_price() {
        return sr_price;
    }

    public void setSr_price(String sr_price) {
        this.sr_price = sr_price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
