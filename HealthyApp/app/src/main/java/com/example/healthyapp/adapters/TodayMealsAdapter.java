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
import com.example.healthyapp.viewholders.TodayMealViewHolder;

import java.util.ArrayList;

public class TodayMealsAdapter extends RecyclerView.Adapter<TodayMealViewHolder> {
    private Context context;
    ArrayList<Meal> meals;

    public TodayMealsAdapter(ArrayList<Meal> meals) {
        this.meals = meals;
    }


    @NonNull
    @Override
    public TodayMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.today_meals_cell, parent, false);
        TodayMealViewHolder todayMealViewHolder = new TodayMealViewHolder(view);
        return todayMealViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayMealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }



    @Override
    public int getItemCount() {
        return this.meals.size();
    }
}
