package com.example.grace.foodwasteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class FindRecipeActivity extends AppCompatActivity {

    private ListView lvRecipes;
    private GetRecipeResponse recipeResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipe);

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("http://api.yummly.com/v1").
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        YummlyClient.YummlyApiInterface client = retrofit.create(YummlyClient.YummlyApiInterface.class);
        Call<GetRecipeResponse> call = client.getRecipeResponse("26edbdd7", "e821c0abc1c766b3cd3f2a2c23023113","onion+soup");
//        Toast.makeText(FindRecipeActivity.this, client.getRecipeResponse("soup").request().url().toString() , Toast.LENGTH_SHORT).show();

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

        registerRecipeClickCallback();
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

    //method is called when the user clicks on a displayed ingredient.
    //a dialogue appears in which the user can edit or delete the ingredient clicked
    private void registerClickCallback(){
        ListView list = (ListView)findViewById(R.id.lvKitchenData);
        //for click on item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

            }
        }
    }
}