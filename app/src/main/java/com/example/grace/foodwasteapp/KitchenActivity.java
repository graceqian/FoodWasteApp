
        package com.example.grace.foodwasteapp;

        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.*;
        import android.content.DialogInterface;

        import java.util.ArrayList;
        import java.util.HashMap;

        public class KitchenActivity extends AppCompatActivity {

    KitchenDatabaseHelper kitchen_database;
    Button btnAddIngredient;
    ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
    HashMap<String, Integer> spinnerPositions;//<units, position>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kitchen);

        spinnerPositions = new HashMap<String, Integer>();
        String[] unitsArray = getResources().getStringArray(R.array.units_array);
        for(int k = 0; k < unitsArray.length; k++){
            spinnerPositions.put(unitsArray[k], k);
        }

        kitchen_database = new KitchenDatabaseHelper(this);

        //fills ingredientList with info from the kitchen_database
        Cursor result = kitchen_database.getAllData();
        while (result.moveToNext()){
            ingredientList.add(new Ingredient(result.getString(0),//ingredient
                    Double.parseDouble(result.getString(1)),//quantity
                    result.getString(2)));//units
        }



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
        registerClickCallback();


    }

    //Puts an ingredient in the database based on the texts of the views passed into the method
    protected void addIngredientData(EditText etIngredient, EditText etQuantity, Spinner spinnerUnits){
        String ingredient  = etIngredient.getText().toString();
        Double quantity = Double.parseDouble(etQuantity.getText().toString());
        String units = spinnerUnits.getSelectedItem().toString();

        boolean isInserted = kitchen_database.insertData(ingredient, quantity, units);
        if (isInserted) {
            Toast.makeText(KitchenActivity.this, "Ingredient added", Toast.LENGTH_LONG).show();
            if(units.equals("none")){
                units = "";
            }
            ingredientList.add(new Ingredient(ingredient, quantity, units));
        }
        else
            Toast.makeText(KitchenActivity.this, "an error has occurred", Toast.LENGTH_LONG).show();
    }

    //populates the listview to display ingredients currently in the kitchen
    private void populateListView(){
        //create list of items
        Cursor result = kitchen_database.getAllData();

//        if there is no data
        if(result.getCount() == 0){
            Toast.makeText(KitchenActivity.this, "You're Kitchen is Empty!", Toast.LENGTH_LONG).show();
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
        //Tutorial Videos:
        //https://www.youtube.com/watch?v=3k3CunDZpFk
        //https://www.youtube.com/watch?v=WRANgDgM2Zg DrBFraser

        public MyListAdapter() {
            super(KitchenActivity.this, R.layout.list_ingredients, ingredientList);
        }

        //gives the passes data to the ListView to display
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_ingredients, parent, false);//create a new view
                //inflater takes a piece of xml code and inflates into an object to be displayed on the screen
            }

            //populating the list

            // Find the ingredient to work with.
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


    //method is called when the user clicks on a displayed ingredient.
    //a dialogue appears in which the user can edit or delete the ingredient clicked
    private void registerClickCallback(){
        ListView list = (ListView)findViewById(R.id.lvKitchenData);
        //for click on item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                //edit dialog appears
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(KitchenActivity.this);
                mBuilder.setCancelable(true);
                View mView = getLayoutInflater().inflate(R.layout.dialog_edit_ingredient, null);


                final Ingredient ingredientClicked = ingredientList.get(position);
                final String ingredient = ingredientClicked.getIngredient();
                final double quantity = ingredientClicked.getQuantity();
                final String units;
                if(ingredientClicked.getUnits().equals("")){
                    units = "none";
                } else{
                    units = ingredientClicked.getUnits();
                }

                //sets text boxes in dialog to match ingredient logged
                ((TextView)mView.findViewById(R.id.tvEditIngredient)).setText(ingredient);
                ((EditText)mView.findViewById(R.id.etEditQuantity)).setText(quantity + "");
                boolean asdf = ((Spinner)mView.findViewById(R.id.spinnerEditUnits)) == null;
                ((Spinner)mView.findViewById(R.id.spinnerEditUnits)).setSelection(spinnerPositions.get(units));

                //define view in layout and change variables to be final
                final TextView tvEditIngredient = ((TextView)mView.findViewById(R.id.tvEditIngredient));
                final EditText etQuantity = (EditText)mView.findViewById(R.id.etEditQuantity);
                final Spinner spinnerUnits = (Spinner)mView.findViewById(R.id.spinnerEditUnits);


                mBuilder.setView(mView);
                final AlertDialog editIngredientDialogue = mBuilder.create();

                //HANDLING UPDATING INGREDIENTS
                Button btnUpdateIngredient = (Button)mView.findViewById(R.id.btnUpdateIngredient);
                btnUpdateIngredient.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String editedIngredient = tvEditIngredient.getText().toString();
                        Double editedQuantity = Double.parseDouble(etQuantity.getText().toString());
                        String editedUnits = spinnerUnits.getSelectedItem().toString();
                        if(!tvEditIngredient.getText().toString().isEmpty() &&
                                !etQuantity.getText().toString().isEmpty()){
//                            kitchen_database.updateData(ingredient, quantity, units);
                            kitchen_database.updateData(ingredient, editedQuantity, editedUnits);
                            //todo somethings not updating, when you go back to the activity its wrong
                            //goes through ingredientList and updates the ingredient in the list
                            for(int k = 0; k < ingredientList.size(); k++){
                                if(ingredientList.get(k).equals(ingredientClicked)){
                                    if(editedUnits.equals("none"))
                                        ingredientList.set(k, new Ingredient(editedIngredient, editedQuantity, ""));
                                    else {
                                        if(editedUnits.equals("none"))
                                            editedUnits = "";
                                        ingredientList.set(k, new Ingredient(editedIngredient, editedQuantity, editedUnits));
                                    }
                                }
                            }
                            kitchen_database.updateData(editedIngredient,editedQuantity, units);
                            populateListView();
                            Toast.makeText(KitchenActivity.this,"Ingredient updated", Toast.LENGTH_SHORT).show();
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
            @Override
            public boolean onCreateOptionsMenu(Menu menu){
                //inflate the menu; this adds items to the action bar if present
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.main_activity_menu, menu);
                return super.onCreateOptionsMenu(menu);
            }

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
}