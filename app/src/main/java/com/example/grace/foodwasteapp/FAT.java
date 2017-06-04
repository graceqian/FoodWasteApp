
package com.example.grace.foodwasteapp;


public class FAT {

    private int min;
    private int max;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FAT() {
    }

    /**
     * 
     * @param min
     * @param max
     */
    public FAT(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
