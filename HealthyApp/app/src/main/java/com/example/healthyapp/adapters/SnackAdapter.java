package com.example.healthyapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.interfaces.OnSnackItemClick;
import com.example.healthyapp.interfaces.OnWorkoutItemClick;
import com.example.healthyapp.models.Meal;
import com.example.healthyapp.models.Snack;
import com.example.healthyapp.viewholders.MealViewHolder;
import com.example.healthyapp.viewholders.SnackViewHolder;

import java.util.ArrayList;

public class SnackAdapter extends RecyclerView.Adapter<SnackViewHolder> {

    ArrayList<Snack> snacks;
    public static OnSnackItemClick onSnackItemClick;
    public SnackAdapter(ArrayList<Snack> sneaks, OnSnackItemClick onSnackItemClick) {
        this.snacks = sneaks;
        this.onSnackItemClick = onSnackItemClick;
    }

    @NonNull
    @Override
    public SnackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.snack_cell, parent, false);
        SnackViewHolder snackViewHolder = new SnackViewHolder(view);
        return snackViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SnackViewHolder holder, int position) {
        Snack snack = snacks.get(position);
        holder.bind(snack);
    }

    @Override
    public int getItemCount() {
       return snacks.size();
    }
}
