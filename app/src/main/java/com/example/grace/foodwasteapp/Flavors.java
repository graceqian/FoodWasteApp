
package com.example.grace.foodwasteapp;


public class Flavors {

    private float salty;
    private float sour;
    private float sweet;
    private float bitter;
    private float meaty;
    private float piquant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Flavors() {
    }

    /**
     * 
     * @param salty
     * @param sour
     * @param sweet
     * @param bitter
     * @param piquant
     * @param meaty
     */
    public Flavors(float salty, float sour, float sweet, float bitter, float meaty, float piquant) {
        super();
        this.salty = salty;
        this.sour = sour;
        this.sweet = sweet;
        this.bitter = bitter;
        this.meaty = meaty;
        this.piquant = piquant;
    }

    public float getSalty() {
        return salty;
    }

    public void setSalty(float salty) {
        this.salty = salty;
    }

    public float getSour() {
        return sour;
    }

    public void setSour(float sour) {
        this.sour = sour;
    }

    public float getSweet() {
        return sweet;
    }

    public void setSweet(float sweet) {
        this.sweet = sweet;
    }

    public float getBitter() {
        return bitter;
    }

    public void setBitter(float bitter) {
        this.bitter = bitter;
    }

    public float getMeaty() {
        return meaty;
    }

    public void setMeaty(float meaty) {
        this.meaty = meaty;
    }

    public float getPiquant() {
        return piquant;
    }

    public void setPiquant(float piquant) {
        this.piquant = piquant;
    }

}
