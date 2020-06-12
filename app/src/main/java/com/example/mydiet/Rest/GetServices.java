package com.example.mydiet.Rest;

import com.example.mydiet.Model.PhotoData;
import com.example.mydiet.Model.Recipe;
import com.example.mydiet.Model.RecipeData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetServices {
    @GET("/photos")
    Call<List<PhotoData>> getAllPhotos();

    @GET("/recipes/findByNutrients")
    Call<List<Recipe>> getRecipe(
            @Query("minCalories") String parameter,
            @Query("maxCalories") String maxCalories,
            @Query("number") Integer number,
            @Query("apiKey") String apiKey
    );
}