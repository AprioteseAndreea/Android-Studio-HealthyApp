package com.example.healthyapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.models.Meal;

public class TodayMealViewHolder extends RecyclerView.ViewHolder {
    private TextView title;

    private ImageView imageView;

    public TodayMealViewHolder(@NonNull View view) {
        super(view);
        title = view.findViewById(R.id.today_meals_title);
        imageView = view.findViewById(R.id.today_meals_image);

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

    }
}