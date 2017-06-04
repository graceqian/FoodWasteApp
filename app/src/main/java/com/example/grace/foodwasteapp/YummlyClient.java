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

public class YummlyClient {
    private static YummlyApiInterface sYummlyService;

    public static YummlyApiInterface getYummlyApiClient(){
        if(sYummlyService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.yummly.com/v1")
                    .build();

            sYummlyService = retrofit.create(YummlyApiInterface.class);
        }

        return sYummlyService;
    }

    public interface YummlyApiInterface {
        // Request method and URL specified in the annotation
        // Callback for the parsed response is the last parameter
        //The base url for the Search Recipes GET is http://api.yummly.com/v1/api/recipes?_app_id=app-id&_app_key=app-key&your _search_parameters
        //?_app_id=26edbdd7&_app_key=e821c0abc1c766b3cd3f2a2c23023113&" + parameters

        //parameters for query search, allowedAllergies, allowedDiets, and allowed Cuisines
        //@Query("allowedAllergy[]") List<String> allowedAllergy, @Query("allowedDiet[]") List<String> allowedDiet, @Query("allowedCuisine") List<String> allowedCuisine,
        @GET("/v1/api/recipes")//at 24 minutes is when he explains the interface
        Call<GetRecipeResponse> getRecipeResponse(@Query("_app_id") String id, @Query("_app_key") String appKey, @Query("q") String search);

//        @GET("group/{id}/users")
//        Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
    }
}
