package com.example.mydiet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiet.Model.Recipe;
import com.example.mydiet.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.PokeItemViewHolder> {


    private List<Recipe> recipeList;
    private Context context;

    public FoodAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    class PokeItemViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        @BindView(R.id.recipeName) TextView textName;
        @BindView(R.id.recipeImage) ImageView imageView;
        @BindView(R.id.textCaloriesCard) TextView textCalories;

        PokeItemViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, mView);
        }
    }

    @NonNull
    @Override
    public PokeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.food_item, parent, false);
        return new PokeItemViewHolder(view);
    }
    public void onBindViewHolder(@NonNull PokeItemViewHolder holder, int position) {

        holder.textName.setText(recipeList.get(position).getTitle());
        holder.textCalories.setText(recipeList.get(position).getCalories() + " Kkal");
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(recipeList.get(position).getImage() )
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
