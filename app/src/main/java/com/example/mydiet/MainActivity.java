package com.example.mydiet;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiet.Adapter.FoodAdapter;
import com.example.mydiet.Model.Recipe;
import com.example.mydiet.Rest.ApiClient;
import com.example.mydiet.Rest.GetServices;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.textWeight)
    TextView textWeight;
    @BindView(R.id.textHeight)
    TextView textHeight;
    @BindView(R.id.textType)
    TextView textType;
    @BindView(R.id.textCalories)
    TextView textCalories;
    @BindView(R.id.textCaloriesBreakfast)
    TextView textCaloriesBreakfast;
    @BindView(R.id.customRecyclerView)
    RecyclerView customRecyclerView;
    @BindView(R.id.textCaloriesLunch)
    TextView textCaloriesLunch;
    @BindView(R.id.lunchRecyclerView)
    RecyclerView lunchRecyclerView;
    @BindView(R.id.textCaloriesDinner)
    TextView textCaloriesDinner;
    @BindView(R.id.DinnerRecyclerView)
    RecyclerView DinnerRecyclerView;

    private FoodAdapter foodAdapter;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressDoalog = new ProgressDialog(MainActivity.this);
        fetchGetBreakFastFood();
        fetchGetLunchFood();
        fetchGetDinnerFood();
    }

    private void fetchGetBreakFastFood() {
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        GetServices services = ApiClient.getFoodRecipe().create(GetServices.class);
        Call<List<Recipe>> call = services.getRecipe("225", 5, "9be917715c3f4ef98debcb575382f043");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> data = response.body();
                progressDoalog.dismiss();
                generateDataList(data);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.d("Error", t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void fetchGetLunchFood() {
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        GetServices services = ApiClient.getFoodRecipe().create(GetServices.class);
        Call<List<Recipe>> call = services.getRecipe("1000", 5, "9be917715c3f4ef98debcb575382f043");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> data = response.body();
                progressDoalog.dismiss();
                generateLunchList(data);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.d("Error", t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void generateLunchList(List<Recipe> recipeList) {
        foodAdapter = new FoodAdapter(recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        lunchRecyclerView.setLayoutManager(layoutManager);
        lunchRecyclerView.setAdapter(foodAdapter);
    }

    private void fetchGetDinnerFood() {
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        GetServices services = ApiClient.getFoodRecipe().create(GetServices.class);
        Call<List<Recipe>> call = services.getRecipe("100", 5, "9be917715c3f4ef98debcb575382f043");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                List<Recipe> data = response.body();
                progressDoalog.dismiss();
                generateDataDinnerList(data);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.d("Error", t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void generateDataDinnerList(List<Recipe> recipeList) {
        foodAdapter = new FoodAdapter(recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        DinnerRecyclerView.setLayoutManager(layoutManager);
        DinnerRecyclerView.setAdapter(foodAdapter);
    }

    private void generateDataList(List<Recipe> recipeList) {
        foodAdapter = new FoodAdapter(recipeList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        customRecyclerView.setLayoutManager(layoutManager);
        customRecyclerView.setAdapter(foodAdapter);
    }
}
