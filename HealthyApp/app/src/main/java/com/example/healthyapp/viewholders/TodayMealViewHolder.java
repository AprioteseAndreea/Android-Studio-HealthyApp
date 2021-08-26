package com.example.healthyapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.adapters.TodayMealsAdapter;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.MealsFragment;
import com.example.healthyapp.models.Meal;
import com.jgabrielfreitas.core.BlurImageView;

public class TodayMealViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView imageView;
    private BlurImageView blurImageView;
    private View view;

    public TodayMealViewHolder(@NonNull View view) {
        super(view);
        title = view.findViewById(R.id.recipe_one);
        imageView = view.findViewById(R.id.img1);
        blurImageView = view.findViewById(R.id.blur_image);
        this.view = view;

    }

    public void bind(Meal meal) {
        title.setText(meal.getName());
        String imageViewUrl = meal.getImagePath();
        ImageLoader imageLoader = VolleyConfigSingleton.getInstance(imageView.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader.get(imageViewUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
               imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Glide.with(this.view).load(imageViewUrl).apply(new RequestOptions().override(250, 250)).into(blurImageView);
        blurImageView.setBlur(50);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.mealClicked=meal;
                if (TodayMealsAdapter.onMealItemClick != null)
                    TodayMealsAdapter.onMealItemClick.onClick(meal);
            }
        });


    }
}
