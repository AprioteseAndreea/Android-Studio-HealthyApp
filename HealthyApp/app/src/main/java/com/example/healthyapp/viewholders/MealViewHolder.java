package com.example.healthyapp.viewholders;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.MealsFragment;
import com.example.healthyapp.models.Meal;

import java.util.Objects;

import static java.security.AccessController.getContext;


public class MealViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView kcals;
    private View view;
    private ImageView imageView;
    private Button addToPreferences;

    public MealViewHolder(@NonNull View view) {
        super(view);
        title = view.findViewById(R.id.meals_name);
        kcals = view.findViewById(R.id.meals_kcals);
        imageView = view.findViewById(R.id.meals_image);
        addToPreferences = view.findViewById(R.id.addToPreferences);

        this.view = view;
    }

    public void bind(Meal meal) {
        title.setText(meal.getName());
        kcals.setText(meal.getCalories());
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.mealClicked=meal;
                if (MealAdapter.onMealItemClick != null)
                    MealAdapter.onMealItemClick.onClick(meal);
            }
        });
        addToPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealAdapter.onAddToPreferencesClick.onClick(meal);

            }
        });
    }
}
