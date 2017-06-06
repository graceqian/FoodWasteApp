package com.example.grace.foodwasteapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setLogo(R.drawable.home_outline_shape);//set logo to home icon
        ab.setDisplayUseLogoEnabled(true);//enable logo
        ab.setDisplayHomeAsUpEnabled(true);//enable home
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu; this adds items to the action bar if present
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //registers if user clicks on menu. If they do, the MainActivity refreshes
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle action bar clicks here. The acitonbar will automatically
        //handle clicks on the Home/Up button, so long as you
        //specify a parent activity in AndroidManifest.xml
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    //if user clicks the "Enter Kitchen" button, they are redirected to KitchenActivity
    protected void onClickEnterKitchen(View v){
        Intent toKitchen = new Intent(this, KitchenActivity.class);
        startActivity(toKitchen);
    }

    //if user clicks the "Find Recipe" button, they are redirected to FindRecipeActivity
    protected void onClickFindRecipe(View v){
        Intent toFindRecipe = new Intent(this, FindRecipeActivity.class);
        startActivity(toFindRecipe);
    }
}
