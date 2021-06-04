package com.example.healthyapp.models;

import com.example.healthyapp.models.dbEntities.HoursSleep;

public class HoursSleepElement {

    private String date;
    private String hours;

    public HoursSleepElement(String date, String hours) {
        this.date = date;
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
    public HoursSleep convert(){ return new HoursSleep(date, hours);}
}
