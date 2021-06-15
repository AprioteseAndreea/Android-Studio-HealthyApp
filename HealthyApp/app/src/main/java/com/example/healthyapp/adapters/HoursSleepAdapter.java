package com.example.healthyapp.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.models.HoursSleepElement;
import com.example.healthyapp.viewholders.HoursSleepViewHolder;

import java.util.ArrayList;

public class HoursSleepAdapter extends RecyclerView.Adapter<HoursSleepViewHolder> {

    ArrayList<HoursSleepElement> hoursList;

    public HoursSleepAdapter(ArrayList<HoursSleepElement> hoursList) {
        this.hoursList = hoursList;
    }

    @NonNull
    @Override
    public HoursSleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hours_sleep_cell, parent, false);
        return new HoursSleepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursSleepViewHolder holder, int position) {
        HoursSleepElement hoursSleepElement = hoursList.get(position);
        holder.bind(hoursSleepElement);
    }

    @Override
    public int getItemCount() {
        return hoursList.size();
    }
}
