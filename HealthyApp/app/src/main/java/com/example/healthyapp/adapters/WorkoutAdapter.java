package com.example.healthyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Workout;
import com.example.healthyapp.viewholders.MealViewHolder;
import com.example.healthyapp.viewholders.TodayMealViewHolder;
import com.example.healthyapp.viewholders.WorkoutViewHolder;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {

    private Context context;
    ArrayList<Workout> workouts;

    public WorkoutAdapter(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.today_workout_cell, parent, false);
        WorkoutViewHolder workoutViewHolder = new WorkoutViewHolder(view);
        return workoutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
}
