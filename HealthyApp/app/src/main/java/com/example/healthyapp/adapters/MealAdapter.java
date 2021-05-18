package com.example.healthyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.interfaces.OnMealItemClick;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.viewholders.MealViewHolder;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealViewHolder> {
    private Context context;
    ArrayList<Meal> meals;
    public static OnMealItemClick onMealItemClick;

    public MealAdapter(ArrayList<Meal> meals, OnMealItemClick onMealItemClick) {
        this.meals = meals;
        this.onMealItemClick = onMealItemClick;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.meals_cell, parent, false);
        MealViewHolder mealViewHolder = new MealViewHolder(view);
        return mealViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return this.meals.size();
    }
}
