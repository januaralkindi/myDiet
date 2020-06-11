package com.example.mydiet.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;
    public static  String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getFoodRecipe(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getFoodImage(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://spoonacular.com/recipeImages/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
