
package com.example.grace.foodwasteapp;


public class AttributeRanges {

    private FlavorPiquant flavorPiquant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AttributeRanges() {
    }

    /**
     * 
     * @param flavorPiquant
     */
    public AttributeRanges(FlavorPiquant flavorPiquant) {
        super();
        this.flavorPiquant = flavorPiquant;
    }

    public FlavorPiquant getFlavorPiquant() {
        return flavorPiquant;
    }

    public void setFlavorPiquant(FlavorPiquant flavorPiquant) {
        this.flavorPiquant = flavorPiquant;
    }

}
