package com.example.urabanapp.model;

import java.io.Serializable;

public class BookingModel implements Serializable {
    public String uid;
    public String pid;
    public String pname;
    public String pic1;
    public int amount;
    public int tot_amount;
    public int c_discount;
    public String time;
    public String date;
    public int status;
    public String c_o;
    public String c_code;
    public String address;
    public String partner_id;
    public String partner_pic;
    public String partner_name;
    public String partner_mobileno;

    public String id;

    public BookingModel(String uid, String pid, String pname, String pic1, int amount, int tot_amount, int c_discount, String time, String date, int status, String c_o, String c_code, String address, String partner_id, String partner_pic, String partner_name, String partner_mobileno, String id) {
        this.uid = uid;
        this.pid = pid;
        this.pname = pname;
        this.pic1 = pic1;
        this.amount = amount;
        this.tot_amount = tot_amount;
        this.c_discount = c_discount;
        this.time = time;
        this.date = date;
        this.status = status;
        this.c_o = c_o;
        this.c_code = c_code;
        this.address = address;
        this.partner_id = partner_id;
        this.partner_pic = partner_pic;
        this.partner_name = partner_name;
        this.partner_mobileno = partner_mobileno;
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(int tot_amount) {
        this.tot_amount = tot_amount;
    }

    public int getC_discount() {
        return c_discount;
    }

    public void setC_discount(int c_discount) {
        this.c_discount = c_discount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getC_o() {
        return c_o;
    }

    public void setC_o(String c_o) {
        this.c_o = c_o;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getPartner_pic() {
        return partner_pic;
    }

    public void setPartner_pic(String partner_pic) {
        this.partner_pic = partner_pic;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_mobileno() {
        return partner_mobileno;
    }

    public void setPartner_mobileno(String partner_mobileno) {
        this.partner_mobileno = partner_mobileno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //    public BookingModel(String uid, String pid, String pname, String pic1, int amount, int tot_amount, int c_discount, String time, String date, int status, String c_o, String c_code, String address, String id) {
//        this.uid = uid;
//        this.pid = pid;
//        this.pname = pname;
//        this.pic1 = pic1;
//        this.amount = amount;
//        this.tot_amount = tot_amount;
//        this.c_discount = c_discount;
//        this.time = time;
//        this.date = date;
//        this.status = status;
//        this.c_o = c_o;
//        this.c_code = c_code;
//        this.address = address;
//        this.id = id;
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public String getPid() {
//        return pid;
//    }
//
//    public void setPid(String pid) {
//        this.pid = pid;
//    }
//
//    public String getPname() {
//        return pname;
//    }
//
//    public void setPname(String pname) {
//        this.pname = pname;
//    }
//
//    public String getPic1() {
//        return pic1;
//    }
//
//    public void setPic1(String pic1) {
//        this.pic1 = pic1;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public int getTot_amount() {
//        return tot_amount;
//    }
//
//    public void setTot_amount(int tot_amount) {
//        this.tot_amount = tot_amount;
//    }
//
//    public int getC_discount() {
//        return c_discount;
//    }
//
//    public void setC_discount(int c_discount) {
//        this.c_discount = c_discount;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getC_o() {
//        return c_o;
//    }
//
//    public void setC_o(String c_o) {
//        this.c_o = c_o;
//    }
//
//    public String getC_code() {
//        return c_code;
//    }
//
//    public void setC_code(String c_code) {
//        this.c_code = c_code;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
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
