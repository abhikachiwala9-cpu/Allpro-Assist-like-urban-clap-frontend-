package com.example.urabanapp.model;

import java.io.Serializable;

public class SubcateModel implements Serializable {


    public String s_name,cat_id,s_pic,id;

    public SubcateModel(String s_name, String cat_id, String s_pic, String id) {
        this.s_name = s_name;
        this.cat_id = cat_id;
        this.s_pic = s_pic;
        this.id = id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getS_pic() {
        return s_pic;
    }

    public void setS_pic(String s_pic) {
        this.s_pic = s_pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//    public String s_name,cat_id,s_pic,id;
//
//    public SubcateModel(String s_name, String cat_id, String s_pic, String id) {
//        this.s_name = s_name;
//        this.cat_id = cat_id;
//        this.s_pic = s_pic;
//        this.id = id;
//    }
//
//    public String getS_name() {
//        return s_name;
//    }
//
//    public void setS_name(String s_name) {
//        this.s_name = s_name;
//    }
//
//    public String getCat_id() {
//        return cat_id;
//    }
//
//    public void setCat_id(String cat_id) {
//        this.cat_id = cat_id;
//    }
//
//    public String getS_pic() {
//        return s_pic;
//    }
//
//    public void setS_pic(String s_pic) {
//        this.s_pic = s_pic;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
}
