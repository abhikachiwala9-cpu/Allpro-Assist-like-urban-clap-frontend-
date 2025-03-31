package com.example.urabanapp.model;

import java.util.ArrayList;

public class DataOutputModel {

    public boolean status;
    public String message;
    public ArrayList<BannerModel> banner_data;
    public ArrayList<CategoryModel> category_data;
    public ArrayList<CoupenModel> coupon_data;
    public ArrayList<SubcateModel> subcat_data;
    public ArrayList<ServicesModel> service_data;
    public ArrayList<PincodeModel> pincode_data;

//    public DataOutputModel(boolean status, String message, ArrayList<BannerModel> banner_data, ArrayList<CategoryModel> category_data, ArrayList<CoupenModel> coupon_data, ArrayList<SubCategorymodel> subcat_data, ArrayList<ServicesModel> service_data, ArrayList<PincodeModel> pincode_data) {
//        this.status = status;
//        this.message = message;
//        this.banner_data = banner_data;
//        this.category_data = category_data;
//        this.coupon_data = coupon_data;
//        this.subcat_data = subcat_data;
//        this.service_data = service_data;
//        this.pincode_data = pincode_data;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public ArrayList<BannerModel> getBanner_data() {
//        return banner_data;
//    }
//
//    public void setBanner_data(ArrayList<BannerModel> banner_data) {
//        this.banner_data = banner_data;
//    }
//
//    public ArrayList<CategoryModel> getCategory_data() {
//        return category_data;
//    }
//
//    public void setCategory_data(ArrayList<CategoryModel> category_data) {
//        this.category_data = category_data;
//    }
//
//    public ArrayList<CoupenModel> getCoupon_data() {
//        return coupon_data;
//    }
//
//    public void setCoupon_data(ArrayList<CoupenModel> coupon_data) {
//        this.coupon_data = coupon_data;
//    }
//
//    public ArrayList<SubcateModel> getSubcat_data() {
//        return subcat_data;
//    }
//
//    public void setSubcat_data(ArrayList<SubcateModel> subcat_data) {
//        this.subcat_data = subcat_data;
//    }
//
//    public ArrayList<ServicesModel> getService_data() {
//        return service_data;
//    }
//
//    public void setService_data(ArrayList<ServicesModel> service_data) {
//        this.service_data = service_data;
//    }
//
//    public ArrayList<PincodeModel> getPincode_data() {
//        return pincode_data;
//    }
//
//    public void setPincode_data(ArrayList<PincodeModel> pincode_data) {
//        this.pincode_data = pincode_data;
//    }


    public DataOutputModel(boolean status, String message, ArrayList<BannerModel> banner_data, ArrayList<CategoryModel> category_data, ArrayList<CoupenModel> coupon_data, ArrayList<SubcateModel> subcat_data, ArrayList<ServicesModel> service_data, ArrayList<PincodeModel> pincode_data) {
        this.status = status;
        this.message = message;
        this.banner_data = banner_data;
        this.category_data = category_data;
        this.coupon_data = coupon_data;
        this.subcat_data = subcat_data;
        this.service_data = service_data;
        this.pincode_data = pincode_data;
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

    public ArrayList<BannerModel> getBanner_data() {
        return banner_data;
    }

    public void setBanner_data(ArrayList<BannerModel> banner_data) {
        this.banner_data = banner_data;
    }

    public ArrayList<CategoryModel> getCategory_data() {
        return category_data;
    }

    public void setCategory_data(ArrayList<CategoryModel> category_data) {
        this.category_data = category_data;
    }

    public ArrayList<CoupenModel> getCoupon_data() {
        return coupon_data;
    }

    public void setCoupon_data(ArrayList<CoupenModel> coupon_data) {
        this.coupon_data = coupon_data;
    }

    public ArrayList<SubcateModel> getSubcat_data() {
        return subcat_data;
    }

    public void setSubcat_data(ArrayList<SubcateModel> subcat_data) {
        this.subcat_data = subcat_data;
    }

    public ArrayList<ServicesModel> getService_data() {
        return service_data;
    }

    public void setService_data(ArrayList<ServicesModel> service_data) {
        this.service_data = service_data;
    }

    public ArrayList<PincodeModel> getPincode_data() {
        return pincode_data;
    }

    public void setPincode_data(ArrayList<PincodeModel> pincode_data) {
        this.pincode_data = pincode_data;
    }
}
