package com.example.grace.foodwasteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class KitchenActivity extends AppCompatActivity {

    KitchenDatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //database = new KitchenDatabaseHelper(this);

        setContentView(R.layout.activity_kitchen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fabAddIngredient = (FloatingActionButton) findViewById(R.id.fabAddIngredient);
        fabAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KitchenActivity.this, AddIngredientActivity.class));
            }
        });

    }
}
