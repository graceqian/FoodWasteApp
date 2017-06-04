
package com.example.grace.foodwasteapp;

import java.util.List;

public class Attributes {

    private List<String> course = null;
    private List<String> cuisine = null;
    private List<String> holiday = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Attributes() {
    }

    /**
     * 
     * @param holiday
     * @param course
     * @param cuisine
     */
    public Attributes(List<String> course, List<String> cuisine, List<String> holiday) {
        super();
        this.course = course;
        this.cuisine = cuisine;
        this.holiday = holiday;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public List<String> getCuisine() {
        return cuisine;
    }

    public void setCuisine(List<String> cuisine) {
        this.cuisine = cuisine;
    }

    public List<String> getHoliday() {
        return holiday;
    }

    public void setHoliday(List<String> holiday) {
        this.holiday = holiday;
    }

}
