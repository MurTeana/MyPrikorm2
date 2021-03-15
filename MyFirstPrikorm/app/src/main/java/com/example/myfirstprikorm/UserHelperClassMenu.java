package com.example.myfirstprikorm;

public class UserHelperClassMenu {

    String data;
    String meal;
    String product;
    String value;

    public String getData() {return data;}
    public String getMeal() {return meal;}
    public String getProduct() {return product;}
    public String getValue() {return value;}

    public void setData(String data) {
        this.data = data;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public UserHelperClassMenu(String data, String meal,String product,String value) {
        this.data = data;
        this.meal = meal;
        this.product = product;
        this.value = value;}

    public UserHelperClassMenu(){

    }

    public String toString(){

        return this.data + "  " + this.meal + "  " + this.product + "  " + this.value;

    }

}