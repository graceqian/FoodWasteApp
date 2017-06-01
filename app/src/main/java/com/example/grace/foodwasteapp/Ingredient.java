package com.example.grace.foodwasteapp;

/**
 * Created by Grace on 5/25/2017.
 */

public class Ingredient {

    private String ingredient, units;
    private double quantity;

    public Ingredient(String ingredient, double quantity, String units) {
        this.ingredient = ingredient;
        this.units = units;
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Ingredient)obj).ingredient.equals(this.ingredient);
    }
}
