package com.example.grace.foodwasteapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.content.DialogInterface;
import android.app.AlertDialog.Builder;

import java.util.ArrayList;

public class KitchenActivity extends AppCompatActivity {

    KitchenDatabaseHelper kitchen_database;
    Button btnAddIngredient;
    TextView KitchenData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kitchen_database = new KitchenDatabaseHelper(this);

        setContentView(R.layout.activity_kitchen);



        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Adding ingredients
        FloatingActionButton fabAddIngredient = (FloatingActionButton) findViewById(R.id.fabAddIngredient);
        fabAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(KitchenActivity.this);
                mBuilder.setCancelable(true);
                final View mView = getLayoutInflater().inflate(R.layout.activity_add_ingredient, null);
                //define view in layout
                final EditText etIngredient = (EditText)mView.findViewById(R.id.etEnterIngredient);
                final EditText etQuantity = (EditText)mView.findViewById(R.id.etEnterQuantity);
                final Spinner spinnerUnits = (Spinner)mView.findViewById(R.id.spinnerUnits);
                btnAddIngredient = (Button)mView.findViewById(R.id.btnAddIngredient);
                mBuilder.setView(mView);
                final AlertDialog addIngredientDialogue = mBuilder.create();
                btnAddIngredient.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(!etIngredient.getText().toString().isEmpty() &&
                                !etQuantity.getText().toString().isEmpty()){
                            addIngredientData(etIngredient, etQuantity, spinnerUnits);
                            addIngredientDialogue.dismiss();
                        } else{
                            Toast.makeText(KitchenActivity.this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                addIngredientDialogue.show();
            }
        });

        //populate the list view
        populateListView();

//        //changes content_activity to display ingredients in the kitchen
//        KitchenData = (TextView)findViewById(R.id.tvKitchenData) ;
//        KitchenData.setText(displayData());

    }


    protected void addIngredientData(EditText etIngredient, EditText etQuantity, Spinner spinnerUnits){
        String ingredient  = etIngredient.getText().toString();
        Double quantity = Double.parseDouble(etQuantity.getText().toString());
        String units = spinnerUnits.getSelectedItem().toString();

        String query = "select * from Kitchen_table where ingredient=\""+ ingredient + "\"";
        Cursor cs = kitchen_database.getDatabase().rawQuery(query, null);
        //if the database already has the ingredient
        if(cs.getCount() != 0){
            AlertDialog alertDialog = new AlertDialog.Builder(KitchenActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Ingredient already in kitchen");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            //TODO make alert centered
            alertDialog.show();
            return;

        }

        boolean isInserted = kitchen_database.insertData(ingredient, quantity, units);
            if (isInserted)
                Toast.makeText(KitchenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(KitchenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
//        KitchenData = (TextView)findViewById(R.id.tvKitchenData) ;
//        KitchenData.setText(displayData());
    }
    //TODO THIS Should be replaced by populate list view later
    //displays data
    public String displayData(){
        Cursor result = kitchen_database.getAllData();

//        if there is no data
        if(result.getCount() == 0){
            //show message
            return "Your kitchen is empty! Press the + to start adding ingredients.";
        }

        StringBuffer buffer = new StringBuffer();
//        get the data one by one
        while (result.moveToNext()){
            buffer.append("Ingredient: " + result.getString(0) + "\n" +
                    "Quantity: " + result.getString(1) + "\n" +
                    "Units: " + result.getString(2) + "\n\n");
        }
        return buffer.toString();
    }

    //populates the listview to display ingredients currently in the kitchen
    private void populateListView(){
        //create list of items
        Cursor result = kitchen_database.getAllData();

//        if there is no data
        if(result.getCount() == 0){
            //TODO show message Your kitchen is empty! Press the + to start adding ingredients.
        }

        ArrayList<String> ingredients = new ArrayList<String>();
        ArrayList<String> quantities = new ArrayList<String>();
        ArrayList<String> units = new ArrayList<String>();

        while (result.moveToNext()){
            ingredients.add(result.getString(0));
            quantities.add(result.getString(1));
            units.add(result.getString(2));
        }

        //build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, //context
                R.layout.da_items, //layout to use
                ingredients); //items to be displayed

        //config list view
//        View rootView = getLayoutInflater().inflate(R.layout.content_kitchen, null);
//        ListView list = (ListView)rootView.findViewById(R.id.lvKitchenData);
        ListView list = (ListView)findViewById(R.id.lvKitchenData);
        list.setAdapter(adapter);//THIS IS WHERE THE PROBLEM LIES
    }
    //TODO add to list view method when you add a new item
}
