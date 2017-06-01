package com.example.grace.foodwasteapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.content.DialogInterface;
import android.app.AlertDialog.Builder;

import java.util.ArrayList;

import static android.support.constraint.R.id.parent;

public class KitchenActivity extends AppCompatActivity {

    KitchenDatabaseHelper kitchen_database;
    Button btnAddIngredient;
//    TextView KitchenData;
    ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();//TODO CHANGE INTO A TREESET?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kitchen_database = new KitchenDatabaseHelper(this);

        setContentView(R.layout.activity_kitchen);

        //fills ingredientList with info from the kitchen_database
        Cursor result = kitchen_database.getAllData();
        while (result.moveToNext()){
            ingredientList.add(new Ingredient(result.getString(0),//ingredient
                    Double.parseDouble(result.getString(1)),//quantity
                    result.getString(2)));//units
        }


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Adding ingredients
        FloatingActionButton fabAddIngredient = (FloatingActionButton) findViewById(R.id.fabAddIngredient);
        fabAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(KitchenActivity.this);
                mBuilder.setCancelable(true);
                final View mView = getLayoutInflater().inflate(R.layout.dialog_add_ingredient, null);
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
        //display data
//        displayData();
        registerClickCallback();

//        //changes content_activity to display ingredients in the kitchen
//        KitchenData = (TextView)findViewById(R.id.tvKitchenData) ;
//        KitchenData.setText(displayData());

    }


    protected void addIngredientData(EditText etIngredient, EditText etQuantity, Spinner spinnerUnits){
        String ingredient  = etIngredient.getText().toString();
        Double quantity = Double.parseDouble(etQuantity.getText().toString());
        String units = spinnerUnits.getSelectedItem().toString();

        if(ingredientExists(ingredient))
            return;

        boolean isInserted = kitchen_database.insertData(ingredient, quantity, units);
            if (isInserted) {
                Toast.makeText(KitchenActivity.this, "Ingredient added", Toast.LENGTH_LONG).show();
                if(units.equals("none")){
                    units = "";
                }
                ingredientList.add(new Ingredient(ingredient, quantity, units));
                populateListView();//updates list view TODO replace line with add new element method??
            }
            else
                Toast.makeText(KitchenActivity.this, "an error has occurred", Toast.LENGTH_LONG).show();
//        KitchenData = (TextView)findViewById(R.id.tvKitchenData) ;
//        KitchenData.setText(displayData());
    }

    //returns true if the ingredient in the parameter already exists in the database
    //and displays a dialog message saying the ingredient already exists
    private boolean ingredientExists(String ingredient){
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
            return true;
        }
        return false;
    }
    //TODO delete poluate list view?
    //displays data
    public String displayData(){
//        Cursor result = kitchen_database.getAllData();
//
////        if there is no data
//        if(result.getCount() == 0){
//            //show message
//            return "Your kitchen is empty! Press the + to start adding ingredients.";
//        }
//
//        StringBuffer buffer = new StringBuffer();
////        get the data one by one
//        while (result.moveToNext()){
//            buffer.append("Ingredient: " + result.getString(0) + "\n" +
//                    "Quantity: " + result.getString(1) + "\n" +
//                    "Units: " + result.getString(2) + "\n\n");
//        }
//        return buffer.toString();
        return null;


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
        ArrayAdapter<Ingredient> adapter = new MyListAdapter();

        //config list view
        ListView list = (ListView)findViewById(R.id.lvKitchenData);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Ingredient>{
            //https://www.youtube.com/watch?v=3k3CunDZpFk
        //https://www.youtube.com/watch?v=WRANgDgM2Zg DrBFraser
        public MyListAdapter() {
                super(KitchenActivity.this, R.layout.da_items, ingredientList);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Make sure we have a view to work with (may have been given null)
                View itemView = convertView;
                if (itemView == null) {
                    itemView = getLayoutInflater().inflate(R.layout.da_items, parent, false);//create a new view
                    //inflater takes a piece of xml code and inflates into an object to be displayed on the screen
                }

                //populating the list

                // Find the car to work with.
                Ingredient currentIngredient = ingredientList.get(position);

                // Fill the view

                // ingredient:
                TextView ingredientText = (TextView) itemView.findViewById(R.id.tvDisplayIngredient);
                ingredientText.setText(currentIngredient.getIngredient());

                // quantity:
                TextView quantityText = (TextView) itemView.findViewById(R.id.tvDisplayQuantity);
                quantityText.setText("" + currentIngredient.getQuantity());

                // units:
                TextView unitsText = (TextView) itemView.findViewById(R.id.tvDisplayUnits);
                unitsText.setText(currentIngredient.getUnits());

                return itemView;
            }
        }
    //TODO add to list view method when you add a new item


    //method is called when the user clicks on a displayed ingredient.
    //a dialogue appears in which the user can edit or delete the ingredient clicked
    private void registerClickCallback(){
        ListView list = (ListView)findViewById(R.id.lvKitchenData);
        //for click on item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
//                TextView textView = (TextView) viewClicked;
//                String message = "You clicked #" + position + " which is string: " + textView.getText().toString();
//                Toast.makeText(KitchenActivity.this, message, Toast.LENGTH_SHORT).show();

                //edit dialog appears
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(KitchenActivity.this);
                mBuilder.setCancelable(true);
                View mView = getLayoutInflater().inflate(R.layout.dialog_edit_ingredient, null);

                //TODO edit text in dialog to match the ingredient the user clicked
//                TextView textView = (TextView) viewClicked;
//                EditText etIngredient = ((EditText)mView.findViewById(R.id.etEditIngredient)).setText(textView.getText().toString());
//                EditText etQuantity = (EditText)mView.findViewById(R.id.etEditQuantity);
//                Spinner spinnerUnits = (Spinner)mView.findViewById(R.id.spinnerUnits);

                final Ingredient ingredientClicked = ingredientList.get(position);
                final String ingredient = ingredientClicked.getIngredient();
                final double quantity = ingredientClicked.getQuantity();
                final String units = ingredientClicked.getUnits();

                //sets text boxes in dialog to match ingredient logged
                ((EditText)mView.findViewById(R.id.etEditIngredient)).setText(ingredient);
                ((EditText)mView.findViewById(R.id.etEditQuantity)).setText(quantity + "");
//                ((Spinner)mView.findViewById(R.id.spinnerUnits)).setSelection(0);//TODO find what positon

                //define view in layout and change variables to be final
                final EditText etIngredient = ((EditText)mView.findViewById(R.id.etEditIngredient));
                final EditText etQuantity = (EditText)mView.findViewById(R.id.etEditQuantity);
                final Spinner spinnerUnits = (Spinner)mView.findViewById(R.id.spinnerUnits);


                mBuilder.setView(mView);
                final AlertDialog editIngredientDialogue = mBuilder.create();

                //HANDLING UPDATING INGREDIENTS
                Button btnUpdateIngredient = (Button)mView.findViewById(R.id.btnUpdateIngredient);
                //todo i change my mind they shouldn't be allowed to edit the ingredient name
                btnUpdateIngredient.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String editedIngredient = etIngredient.getText().toString();
                        Double editedQuantity = Double.parseDouble(etQuantity.getText().toString());
//                        String editedUnits = spinnerUnits.getSelectedItem().toString();
                        if(!etIngredient.getText().toString().isEmpty() &&
                                !etQuantity.getText().toString().isEmpty()){
                            //TODO UPDATE INSTEAD OF ADD
                            kitchen_database.updateData(ingredient, quantity, units);
                            //goes through ingredientList and updates the ingredient in the list
                            for(int k = 0; k < ingredientList.size(); k++){
                                if(ingredientList.get(k).equals(ingredientClicked)){
                                    if(units.equals("none"))
                                        ingredientList.set(k, new Ingredient(editedIngredient, editedQuantity, ""));
                                    else
                                        ingredientList.set(k, new Ingredient(editedIngredient, editedQuantity, units));//todo change to editedUnits
                                }
                            }
                            kitchen_database.updateData(editedIngredient,editedQuantity, units);
                            populateListView();
                            //TODO if they changed the ingredient name, add the data and remove that row with the old ingredient name
                            editIngredientDialogue.dismiss();
                        } else{
                            Toast.makeText(KitchenActivity.this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                //HANDLING DELETING INGREDIENTS
                Button btnDeleteIngredient = (Button)mView.findViewById(R.id.btnDeleteIngredient);
                btnDeleteIngredient.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        kitchen_database.deleteIngredient(ingredient);
                        Toast.makeText(KitchenActivity.this, "Ingredient removed", Toast.LENGTH_SHORT).show();
                        //goes through ingredientList and updates the ingredient in the list
                        for(int k = 0; k < ingredientList.size(); k++){
                            if(ingredientList.get(k).getIngredient().equals(ingredient)){
                                ingredientList.remove(k);
                            }
                        }
                        populateListView();
                        editIngredientDialogue.dismiss();
                    }
                });

                editIngredientDialogue.show();

            }
        });
    }
}
