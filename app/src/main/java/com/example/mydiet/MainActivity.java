package com.example.mydiet;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.mydiet.Model.UserModel;
import com.example.mydiet.Rest.ApiClient;
import com.example.mydiet.Rest.GetServices;
import com.example.mydiet.SQLHelper.UserHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    @BindView(R.id.txtToday)
    TextView txtToday;

    private FoodAdapter foodAdapter;
    ProgressDialog progressDoalog;
    private UserHelper userHelper;
    private UserModel dataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        long userId = intent.getLongExtra("id", 1);
        progressDoalog = new ProgressDialog(MainActivity.this);
        userHelper = new UserHelper(this);
        dataUser = userHelper.getUserById(userId);
        setUpdateTime();
        setUpDataUser(dataUser);
        fetchGetBreakFastFood(dataUser.getKalori() * 0.3);
        fetchGetLunchFood(dataUser.getKalori() * 0.5);
        fetchGetDinnerFood(dataUser.getKalori() * 0.2);
    }

    private void setUpdateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        String date = dateFormat.format(calendar.getTime());
        txtToday.setText(date);
    }

    private void setUpDataUser(UserModel dataUser) {
        textHeight.setText(dataUser.getTinggi().toString() + " cm");
        textWeight.setText(dataUser.getBerat().toString() + "Kg");
        int calories = (int) Math.ceil(dataUser.getKalori());
        textCalories.setText(String.valueOf(calories) + "Kkkal");
    }

    private void fetchGetBreakFastFood(double kaloriBreakFast) {
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        int intKaloriBf = (int) Math.ceil(kaloriBreakFast);
        String kaloriBf = String.valueOf(intKaloriBf);
        textCaloriesBreakfast.setText(kaloriBf + " Kkal");
        GetServices services = ApiClient.getFoodRecipe().create(GetServices.class);
        Call<List<Recipe>> call = services.getRecipe(kaloriBf, String.valueOf(intKaloriBf + 50), 10, "9be917715c3f4ef98debcb575382f043");
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

    private void fetchGetLunchFood(double kaloriLunch) {
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        int intKaloriLnch = (int) Math.ceil(kaloriLunch);
        String kaloriLnch = String.valueOf((int) Math.ceil(intKaloriLnch));
        textCaloriesLunch.setText(kaloriLnch + " Kkal");
        GetServices services = ApiClient.getFoodRecipe().create(GetServices.class);
        Call<List<Recipe>> call = services.getRecipe(kaloriLnch, String.valueOf(intKaloriLnch + 50), 10, "9be917715c3f4ef98debcb575382f043");
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

    private void fetchGetDinnerFood(double kaloriDinner) {
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        int intKaloriDn = (int) Math.ceil(kaloriDinner);
        String kaloriDn = String.valueOf(intKaloriDn);

        textCaloriesDinner.setText(kaloriDn + " Kkal");

        GetServices services = ApiClient.getFoodRecipe().create(GetServices.class);
        Call<List<Recipe>> call = services.getRecipe(kaloriDn, String.valueOf(intKaloriDn + 50), 10, "9be917715c3f4ef98debcb575382f043");
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
