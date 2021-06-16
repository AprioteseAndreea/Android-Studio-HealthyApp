package com.example.healthyapp.viewholders;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.adapters.TodayWorkoutAdapter;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.models.Workout;

public class TodayWorkoutViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private View view;

    public TodayWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.today_workout);
        this.view = itemView;
    }

    public void bind(Workout workout) {
        textView.setText(workout.getName());
       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               HomeFragment.workoutClicked= workout.getId();
               if (TodayWorkoutAdapter.onWorkoutItemClick != null)
                   TodayWorkoutAdapter.onWorkoutItemClick.onClick(workout);
           }
       });
    }
}
