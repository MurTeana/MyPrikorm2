package com.example.myfirstprikorm.model;

public class PrikormList {
    private Integer id;
    private int idUser;
    private String dateMeal;
    private String meal;
    private String product;
    private int weight;
    private String reaction;

    public PrikormList(int idUser, String dateMeal, String meal, String product, int weight, String reaction) {
        this.idUser = idUser;
        this.dateMeal = dateMeal;
        this.meal = meal;
        this.product = product;
        this.weight = weight;
        this.reaction = reaction;
    }

    public Integer getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getDateMeal() {
        return dateMeal;
    }

    public String getMeal() {
        return meal;
    }

    public String getProduct() {
        return product;
    }

    public int getWeight() {
        return weight;
    }

    public String getReaction() {
        return reaction;
    }
}
