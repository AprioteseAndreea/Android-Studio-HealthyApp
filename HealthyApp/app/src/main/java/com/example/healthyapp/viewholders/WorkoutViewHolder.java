package com.example.healthyapp.viewholders;

import android.media.Image;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.adapters.WorkoutAdapter;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.fragments.WorkoutFragment;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Workout;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView imageView;
    private View view;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.workout_title);
        imageView = itemView.findViewById(R.id.workout_image);
        this.view = itemView;
    }

    public void bind(Workout workout) {
        title.setText(workout.getName());
        String imageViewUrl = workout.getBackground();
        Glide.with(this.view).load(imageViewUrl).apply(new RequestOptions().override(200, 200)).into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.workoutClicked = workout.getId();
                if (WorkoutAdapter.onWorkoutItemClick != null)
                    WorkoutAdapter.onWorkoutItemClick.onClick(workout);
            }
        });
    }
}
