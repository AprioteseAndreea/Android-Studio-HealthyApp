package com.example.healthyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.interfaces.OnWorkoutItemClick;
import com.example.healthyapp.models.Workout;
import com.example.healthyapp.viewholders.WorkoutViewHolder;

import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
    ArrayList<Workout> workouts;
    public static OnWorkoutItemClick onWorkoutItemClick;

    public WorkoutAdapter(ArrayList<Workout> workouts, OnWorkoutItemClick onWorkoutItemClick) {
        this.workouts = workouts;
        this.onWorkoutItemClick = onWorkoutItemClick;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.workout_cell, parent, false);
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
