package com.example.urabanapp.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    public String category_name,category_pic,id;

    public CategoryModel(String category_name, String category_pic, String id) {
        this.category_name = category_name;
        this.category_pic = category_pic;
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_pic() {
        return category_pic;
    }

    public void setCategory_pic(String category_pic) {
        this.category_pic = category_pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
