package com.example.healthyapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.healthyapp.R;
import com.example.healthyapp.VolleyConfigSingleton;
import com.example.healthyapp.adapters.MealAdapter;
import com.example.healthyapp.fragments.MealsFragment;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Snack;

import de.hdodenhof.circleimageview.CircleImageView;

public class SnackViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView imageView;
    private View view;

    public SnackViewHolder(@NonNull View view) {
        super(view);
        imageView = view.findViewById(R.id.snack_image);
        this.view = view;

    }

    public void bind(Snack snack) {
        String imageViewUrl = snack.getPath();
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


    }
}
