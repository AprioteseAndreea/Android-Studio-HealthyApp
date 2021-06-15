package com.example.healthyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.interfaces.OnWorkoutItemClick;
import com.example.healthyapp.models.Workout;
import com.example.healthyapp.viewholders.TodayWorkoutViewHolder;

import java.util.ArrayList;

public class TodayWorkoutAdapter extends RecyclerView.Adapter<TodayWorkoutViewHolder> {
    ArrayList<Workout> workouts;
    public static OnWorkoutItemClick onWorkoutItemClick;

    public TodayWorkoutAdapter(ArrayList<Workout> workouts, OnWorkoutItemClick onWorkoutIemClick) {
        this.workouts = workouts;
        this.onWorkoutItemClick = onWorkoutIemClick;
    }

    @NonNull
    @Override
    public TodayWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.today_workout_cell, parent, false);
        TodayWorkoutViewHolder workoutViewHolder = new TodayWorkoutViewHolder(view);
        return workoutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayWorkoutViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        holder.bind(workout);
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
}
