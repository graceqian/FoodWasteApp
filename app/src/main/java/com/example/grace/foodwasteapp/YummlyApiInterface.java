package com.example.grace.foodwasteapp;

/**
 * Created by Grace on 6/2/2017.
 */
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.Callback;
import retrofit.*;
import java.util.*;
    public interface YummlyApiInterface {
        // Request method and URL specified in the annotation
        // Callback for the parsed response is the last parameter

        //parameters for query search, allowedAllergies, allowedDiets, and allowed Cuisines
        @GET("/v1/api/recipes")
        Call<GetRecipeResponse> getRecipeResponse(@Query("_app_id") String id,
                                                  @Query("_app_key") String appKey,
                                                  @Query("q") String search,
                                                  @Query(value = "allowedAllergy%5b%5d", encoded = true) List<String> allergies,
                                                  @Query(value = "allowedDiet%5b%5d", encoded = true) List<String> diets,
                                                  @Query(value = "allowedCuisine%5b%5d", encoded = true) List<String> cuisine);

    }
