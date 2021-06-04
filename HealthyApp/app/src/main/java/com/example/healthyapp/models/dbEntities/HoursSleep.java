package com.example.healthyapp.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.healthyapp.models.HoursSleepElement;

@Entity
public class HoursSleep {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "hours")
    public String hours;

    public HoursSleep(String date, String hours) {

        this.date = date;
        this.hours = hours;
    }
    public HoursSleepElement convert(){ return new HoursSleepElement(date, hours);}
}
