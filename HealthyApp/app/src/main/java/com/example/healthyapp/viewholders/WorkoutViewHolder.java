package com.example.healthyapp.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.models.Workout;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private View view;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.today_workout);
        this.view = itemView;
    }

    public void bind(Workout workout) {
      textView.setText(workout.getName());
    }
}
