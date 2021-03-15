package com.example.myfirstprikorm;

public class UserHelperClassProd {

    String product;

    public String getProduct() {return product;}

    public void setProduct(String product) {
        this.product = product;
    }

    public UserHelperClassProd(String product) {this.product = product;}

    public UserHelperClassProd(){
    }

    public String toString(){
        return this.product;
    }

}