package com.example.myfirstprikorm.data.ViewModel;

public class Product {
    private Integer id;
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
