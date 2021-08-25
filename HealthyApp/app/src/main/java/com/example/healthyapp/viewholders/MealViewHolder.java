package com.example.healthyapp.viewholders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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

    public Button getAddToPreferences() {
        return addToPreferences;
    }

    public void setAddToPreferences(Button addToPreferences) {
        this.addToPreferences = addToPreferences;
    }

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
        Glide.with(this.view).load(imageViewUrl).apply(new RequestOptions().override(150, 150)).into(imageView);
        if (meal.getFavourite()) {

            Drawable buttonDrawable = addToPreferences.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.BLACK);
            addToPreferences.setBackground(buttonDrawable);

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.mealClicked = meal;
                if (MealAdapter.onMealItemClick != null)
                    MealAdapter.onMealItemClick.onClick(meal);
            }
        });
        addToPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MealAdapter.onAddToPreferencesClick.onClick(meal);

//                if (title.getText().equals(MealsFragment.favouritesMeals.get(MealsFragment.favouritesMeals.size()-1))) {
//                    Drawable buttonDrawable = addToPreferences.getBackground();
//                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
//                    DrawableCompat.setTint(buttonDrawable, Color.BLACK);
//                    addToPreferences.setBackground(buttonDrawable);
//
//
//                }
            }
        });
    }
}
