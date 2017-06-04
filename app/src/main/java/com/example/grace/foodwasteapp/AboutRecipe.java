package com.example.grace.foodwasteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutRecipe extends AppCompatActivity {

    //TODO WHEN A RECIPE IS CLICKED, AN INTENT IS PASSED HERE WITH THE MATCH INFORMATION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_recipe);
    }
}