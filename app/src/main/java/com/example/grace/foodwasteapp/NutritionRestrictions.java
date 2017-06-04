
package com.example.grace.foodwasteapp;


public class NutritionRestrictions {

    private FAT fAT;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NutritionRestrictions() {
    }

    /**
     * 
     * @param fAT
     */
    public NutritionRestrictions(FAT fAT) {
        super();
        this.fAT = fAT;
    }

    public FAT getFAT() {
        return fAT;
    }

    public void setFAT(FAT fAT) {
        this.fAT = fAT;
    }

}
