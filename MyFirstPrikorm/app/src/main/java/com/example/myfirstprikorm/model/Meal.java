package com.example.myfirstprikorm.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    private Integer id;

    //@SerializedName("body")
    private String product_;

    public Product(String product_) {
        this.product_ = product_;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product_;
    }

    public void setProduct(String product) {
        product_ = product;
    }


}
