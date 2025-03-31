package com.example.urabanapp.model;

import java.io.Serializable;

public class CoupenModel implements Serializable {

    public String c_code,c_desc,c_title,c_discount,c_maxamt,c_pic,id;

    public boolean status;

    public CoupenModel(String c_code, String c_desc, String c_title, String c_discount, String c_maxamt, String c_pic, String id, boolean status) {
        this.c_code = c_code;
        this.c_desc = c_desc;
        this.c_title = c_title;
        this.c_discount = c_discount;
        this.c_maxamt = c_maxamt;
        this.c_pic = c_pic;
        this.id = id;
        this.status = status;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public String getC_discount() {
        return c_discount;
    }

    public void setC_discount(String c_discount) {
        this.c_discount = c_discount;
    }

    public String getC_maxamt() {
        return c_maxamt;
    }

    public void setC_maxamt(String c_maxamt) {
        this.c_maxamt = c_maxamt;
    }

    public String getC_pic() {
        return c_pic;
    }

    public void setC_pic(String c_pic) {
        this.c_pic = c_pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
