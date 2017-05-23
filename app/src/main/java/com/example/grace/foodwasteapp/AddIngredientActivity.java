package com.example.grace.foodwasteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.grace.foodwasteapp.R.id.etEnterIngredient;

public class AddIngredientActivity extends AppCompatActivity {

    KitchenDatabaseHelper kitchen_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindow().setLayout((int)(width * .8),(int)(height *.5));//makes dimensions of activity smaller so it will be a pop up dialogue

        //gets kitchen_database from intent from KitchenActivity
//        Intent intent = this.getIntent(); TODO delete later
//        Bundle bundle = intent.getExtras();
//        kitchen_database = (KitchenDatabaseHelper)bundle.getSerializable("kitchen_database");
        //kitchen_database = (KitchenDatabaseHelper)getIntent().getSerializableExtra("kitchen_database");
    }

//    protected void onClickAddIngredient(View v){
////        String ingredient = ((EditText)findViewById(R.id.etEnterIngredient)).getText().toString();
////        Double quantity = Double.valueOf(((EditText)findViewById(R.id.etEnterQuantity)).getText().toString());
////        String units = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();
////
////        boolean isInserted = kitchen_database.insertData(ingredient, quantity, units);
////        if (isInserted)
////            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
////        else
////            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
////
////        Intent toKitchen = new Intent(this, KitchenActivity.class);
////        toKitchen.putExtra("ingredient", ingredient);
////        toKitchen.putExtra("quantity", quantity);
////        toKitchen.putExtra("units", units);
////        startActivity(toKitchen);
//        //TODO delete later
//    }
}
