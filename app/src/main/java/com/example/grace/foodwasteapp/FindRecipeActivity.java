package com.example.grace.foodwasteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class FindRecipeActivity extends AppCompatActivity {

    private ListView lvRecipes;
    private GetRecipeResponse recipeResponse;
    private final String APP_ID = "26edbdd7";
    private final String APP_KEY = "e821c0abc1c766b3cd3f2a2c23023113";
    private YummlyApiInterface client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipe);



        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://api.yummly.com/v1").
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        client = retrofit.create(YummlyApiInterface.class);
        SearchView sv = (SearchView)findViewById(R.id.svRecipeSearch);
        if(getIntent().getStringExtra("query") == null){
            sv.setIconified(false);
        } else{
            sv.setQuery(getIntent().getStringExtra("query"), true);
            callApi(getIntent().getStringExtra("query"));
        }

//        handling queries in search bar
        final SearchView searchView = sv;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                callSearch(newText);
//              }
                return true;
            }

            public void callSearch(String query) {
                callApi(query);
            }

        });

        registerRecipeClickCallback();
    }


    public void callApi(String query){
        List<String> allergiesSelected = getIntent().getStringArrayListExtra("allergies selected");
        List<String> dietsSelected = getIntent().getStringArrayListExtra("diets selected");
        List<String> cuisinesSelected = getIntent().getStringArrayListExtra("cuisines selected");
        Call<GetRecipeResponse> call = client.getRecipeResponse(APP_ID, APP_KEY, query, allergiesSelected, dietsSelected, cuisinesSelected);

        //must call api asynchronously because there is a UI thread
        call.enqueue(new Callback<GetRecipeResponse>() {
            @Override
            public void onResponse(Response<GetRecipeResponse> response, Retrofit retrofit) {
                recipeResponse = response.body();
                if(recipeResponse == null)
                    Toast.makeText(FindRecipeActivity.this, "its null" , Toast.LENGTH_SHORT).show();

                //build adapter
                ArrayAdapter<Match> adapter = new RecipeAdapter();

                //config list view
                ListView list = (ListView)findViewById(R.id.lvRecipes);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(FindRecipeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class RecipeAdapter extends ArrayAdapter<Match> {

        public RecipeAdapter() {
            super(FindRecipeActivity.this, R.layout.list_recipes, recipeResponse.getMatches());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_recipes, parent, false);//create a new view
                //inflater takes a piece of xml code and inflates into an object to be displayed on the screen
            }

            //populating the list

            // Find the car to work with.
            Match currentMatch = recipeResponse.getMatches().get(position);

            // Fill the view

            // Recipe name:
            TextView recipeText = (TextView) itemView.findViewById(R.id.tvAboutRecipeName);
            recipeText.setText(currentMatch.getRecipeName());

            //time to make recipe:
            TextView timeText = (TextView) itemView.findViewById(R.id.tvRecipeTime);
            int timeInMinutes = currentMatch.getTotalTimeInSeconds()/60;
            int hours = timeInMinutes / 60;
            int minutes = timeInMinutes % 60;
            timeText.setText(hours + " hrs " + minutes + " min");

            //recipe rating
            TextView ratingText = (TextView) itemView.findViewById(R.id.tvRecipeRating);
            ratingText.setText("Rating: " + currentMatch.getRating());


            return itemView;
        }
    }

    //method is called when the user clicks on a displayed recipe
    //the app switches to an overview of the recipe
    private void registerRecipeClickCallback(){
        ListView list = (ListView)findViewById(R.id.lvRecipes);
        //for click on item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Match match = recipeResponse.getMatches().get(position);//match clicked
                Intent toAboutRecipe = new Intent(FindRecipeActivity.this, AboutRecipeActivity.class);
                toAboutRecipe.putExtra("name", match.getRecipeName());
                toAboutRecipe.putExtra("rating", match.getRating() + "");
                toAboutRecipe.putStringArrayListExtra("ingredients", (ArrayList<String>)match.getIngredients());
                toAboutRecipe.putExtra("cuisine", (ArrayList)match.getAttributes().getCuisine());
                toAboutRecipe.putExtra("id", match.getId());
                int timeInMinutes = match.getTotalTimeInSeconds()/60;
                int hours = timeInMinutes / 60;
                int minutes = timeInMinutes % 60;
                toAboutRecipe.putExtra("time", hours + " hrs " + minutes + " min");
                startActivity(toAboutRecipe);
            }
        });
    }

    //WHen users click the filters button, they are redirected to the FiltersActivity
    //the query is passed to the FitlersActivity
    protected void onClickFilters(View v){
        Intent toFilters = new Intent(this, FiltersActivity.class);
        SearchView sv = (SearchView)findViewById(R.id.svRecipeSearch);
        toFilters.putExtra("query", sv.getQuery().toString());
        startActivity(toFilters);
    }

    //create options menu
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