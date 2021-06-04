package com.example.healthyapp.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyapp.R;
import com.example.healthyapp.models.HoursSleepElement;

public class HoursSleepViewHolder extends RecyclerView.ViewHolder {

    private TextView date;
    private TextView hours;


    public HoursSleepViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.date);
        hours = itemView.findViewById(R.id.hours);

    }

    public void bind(HoursSleepElement hoursSleepElement){
        date.setText(hoursSleepElement.getDate());
        hours.setText(hoursSleepElement.getHours());

    }
}
