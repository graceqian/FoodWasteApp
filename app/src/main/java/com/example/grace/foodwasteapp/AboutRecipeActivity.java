package com.example.grace.foodwasteapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AboutRecipeActivity extends AppCompatActivity {

    private List<String> recipeIngredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_recipe);

        Intent intent = getIntent();
        recipeIngredientList = intent.getStringArrayListExtra("ingredients");

        //retrieve and display information about the match (recipe)
        ((TextView)findViewById(R.id.tvAboutRecipeName)).setText(intent.getStringExtra("name"));//recipe name
        ((TextView)findViewById(R.id.tvAboutRecipeTime)).setText(intent.getStringExtra("time"));//recipe time
        ((TextView)findViewById(R.id.tvAboutRecipeRating)).setText("Rating: " + intent.getStringExtra("rating"));//recipe rating
        ((TextView)findViewById(R.id.tvIngredientsInRecipe)).setText(recipeIngredientList.size() + " Ingredients");//recipe rating
        if(intent.getStringExtra("cuisine") != null)
            ((TextView)findViewById(R.id.tvRecipeCuisine)).setText("Cuisine: " + intent.getStringExtra("cuisine"));//recipe cuisine


        //build adapter
        ArrayAdapter<String> adapter = new RecipeIngredientAdapter();

        //config list view
        ListView list = (ListView)findViewById(R.id.lvRecipeIngredients);
        list.setAdapter(adapter);

    }

    private class RecipeIngredientAdapter extends ArrayAdapter<String> {
        //https://www.youtube.com/watch?v=3k3CunDZpFk
        //https://www.youtube.com/watch?v=WRANgDgM2Zg DrBFraser
        public RecipeIngredientAdapter() {
            super(AboutRecipeActivity.this, R.layout.list_simple, recipeIngredientList);
        }

        //Get a View that displays the data at the specified position in the data set.
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_simple, parent, false);//create a new view
                //inflater takes a piece of xml code and inflates into an object to be displayed on the screen
            }

            //populating the list

            // Find the ingredient to work with.
            String currentIngredient = recipeIngredientList.get(position);

            // Fill the view

            // ingredient:
            TextView ingredientText = (TextView) itemView.findViewById(R.id.tvSimpleItem);
            ingredientText.setText(currentIngredient);

            return itemView;
        }
    }

    //When the "make Recipe" button is pressed, the user is redirected to a webpage of the recipe
    protected void onClickMakeRecipe(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.yummly.com/recipe/" + getIntent().getStringExtra("id")));
        startActivity(intent);
    }

    //create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu; this adds items to the action bar if present
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //when home button is pressed, user is redirected to the main activity
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