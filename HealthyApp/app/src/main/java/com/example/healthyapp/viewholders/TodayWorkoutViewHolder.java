package com.example.healthyapp.viewholders;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
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
        ImageLoader imageLoader = VolleyConfigSingleton.getInstance(imageView.getContext().
                getApplicationContext()).getImageLoader();
        imageLoader.get(imageViewUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
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
