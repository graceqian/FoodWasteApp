package com.example.grace.foodwasteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onClickEnterKitchen(View v){
        Intent toKitchen = new Intent(this, KitchenActivity.class);
        startActivity(toKitchen);
    }
}
