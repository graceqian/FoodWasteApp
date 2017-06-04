
package com.example.grace.foodwasteapp;


public class FlavorPiquant {

    private float min;
    private int max;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FlavorPiquant() {
    }

    /**
     * 
     * @param min
     * @param max
     */
    public FlavorPiquant(float min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
