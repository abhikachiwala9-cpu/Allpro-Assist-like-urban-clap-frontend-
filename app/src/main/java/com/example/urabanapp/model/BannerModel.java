package com.example.urabanapp.model;

public class BannerModel {

    public boolean status;
    public String banner_name,banner_pic,id;

    public BannerModel(boolean status, String banner_name, String banner_pic, String id) {
        this.status = status;
        this.banner_name = banner_name;
        this.banner_pic = banner_pic;
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBanner_name() {
        return banner_name;
    }

    public void setBanner_name(String banner_name) {
        this.banner_name = banner_name;
    }

    public String getBanner_pic() {
        return banner_pic;
    }

    public void setBanner_pic(String banner_pic) {
        this.banner_pic = banner_pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
