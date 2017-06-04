
package com.example.grace.foodwasteapp;

import java.util.List;

public class Match {

    private Attributes attributes;
    private Flavors flavors;
    private int rating;
    private String id;
    private List<String> smallImageUrls = null;
    private String sourceDisplayName;
    private int totalTimeInSeconds;
    private List<String> ingredients = null;
    private String recipeName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Match() {
    }

    /**
     * 
     * @param ingredients
     * @param id
     * @param recipeName
     * @param totalTimeInSeconds
     * @param smallImageUrls
     * @param sourceDisplayName
     * @param flavors
     * @param rating
     * @param attributes
     */
    public Match(Attributes attributes, Flavors flavors, int rating, String id, List<String> smallImageUrls, String sourceDisplayName, int totalTimeInSeconds, List<String> ingredients, String recipeName) {
        super();
        this.attributes = attributes;
        this.flavors = flavors;
        this.rating = rating;
        this.id = id;
        this.smallImageUrls = smallImageUrls;
        this.sourceDisplayName = sourceDisplayName;
        this.totalTimeInSeconds = totalTimeInSeconds;
        this.ingredients = ingredients;
        this.recipeName = recipeName;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Flavors getFlavors() {
        return flavors;
    }

    public void setFlavors(Flavors flavors) {
        this.flavors = flavors;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(List<String> smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    public int getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public void setTotalTimeInSeconds(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

}
