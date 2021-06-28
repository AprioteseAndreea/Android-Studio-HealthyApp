package com.example.healthyapp.viewholders;

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
import com.example.healthyapp.adapters.TodayWorkoutAdapter;
import com.example.healthyapp.fragments.HomeFragment;
import com.example.healthyapp.models.Workout;

public class TodayWorkoutViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;
    private View view;

    public TodayWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.today_workout);
        imageView = itemView.findViewById(R.id.today_workout_image);
        this.view = itemView;
    }

    public void bind(Workout workout) {
        textView.setText(workout.getName());
        String imageViewUrl = workout.getBackground();
        Glide.with(this.view).load(imageViewUrl).apply(new RequestOptions().override(150, 150)).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.workoutClicked = workout.getId();
                if (TodayWorkoutAdapter.onWorkoutItemClick != null)
                    TodayWorkoutAdapter.onWorkoutItemClick.onClick(workout);
            }
        });
    }
}
