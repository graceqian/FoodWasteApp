package com.example.grace.foodwasteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class KitchenActivity extends AppCompatActivity {

    KitchenDatabaseHelper kitchen_database;
    Button btnAddIngredient;

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
                //TODO delete this trash
//                Intent toAddIngredient = new Intent(KitchenActivity.this, AddIngredientActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("kitchen_database", kitchen_database);
//                toAddIngredient.putExtras(bundle);
//                startActivity(toAddIngredient);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(KitchenActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.activity_add_ingredient, null);
                //define view in layout
                final EditText etIngredient = (EditText)mView.findViewById(R.id.etEnterIngredient);
                final EditText etQuantity = (EditText)mView.findViewById(R.id.etEnterQuantity);
                final Spinner spinnerUnits = (Spinner)mView.findViewById(R.id.spinnerUnits);
                btnAddIngredient = (Button)mView.findViewById(R.id.btnAddIngredient);
                btnAddIngredient.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(!etIngredient.getText().toString().isEmpty() &&
                                !etQuantity.getText().toString().isEmpty()){
                            addIngredientData(etIngredient, etQuantity, spinnerUnits);
                        } else{
                            Toast.makeText(KitchenActivity.this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                mBuilder.setView(mView);
                AlertDialog addIngredientDialogue = mBuilder.create();
                addIngredientDialogue.show();
            }
        });

    }

    protected void addIngredientData(EditText etIngredient, EditText etQuantity, Spinner spinnerUnits){
        String ingredient  = etIngredient.getText().toString();
        Double quantity = Double.parseDouble(etQuantity.getText().toString());
        String units = spinnerUnits.getSelectedItem().toString();
        boolean isInserted = kitchen_database.insertData(ingredient, quantity, units);
            if (isInserted)
                Toast.makeText(KitchenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(KitchenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

//        btnAddIngredient.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        View mView = getLayoutInflater().inflate(R.layout.activity_add_ingredient, null);
//                        //define view in layout
//                        String ingredient = ((EditText)mView.findViewById(R.id.etEnterIngredient)).getText().toString();
//                        Double quantity = Double.parseDouble(((EditText)mView.findViewById(R.id.etEnterQuantity)).getText().toString());
//                        String units = ((Spinner)mView.findViewById(R.id.spinnerUnits)).getSelectedItem().toString();
//
////                        String ingredient = ((EditText) findViewById(R.id.etEnterIngredient)).getText().toString();
////                        Double quantity = Double.valueOf(((EditText) findViewById(R.id.etEnterQuantity)).getText().toString());
////                        String units = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();
//                        boolean isInserted = kitchen_database.insertData(ingredient, quantity, units);
//                        if (isInserted)
//                            Toast.makeText(KitchenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(KitchenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    }
}
