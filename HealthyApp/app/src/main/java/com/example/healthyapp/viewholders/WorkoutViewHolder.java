package com.example.healthyapp.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.adapters.WorkoutAdapter;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.WorkoutFragment;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Workout;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {
    private TextView time;
    private TextView day;
    private TextView title;
    private View view;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.workout_time);
        day = itemView.findViewById(R.id.workout_day);
        title = itemView.findViewById(R.id.workout_title);
        this.view = itemView;
    }

    public void bind(Workout workout) {
        time.setText(workout.getTime());
        day.setText(workout.getDay());
        title.setText(workout.getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutFragment.workoutId = workout.getName();
                if (WorkoutAdapter.onWorkoutItemClick != null)
                    WorkoutAdapter.onWorkoutItemClick.onClick(workout);
            }
        });
    }
}
