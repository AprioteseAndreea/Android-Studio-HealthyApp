package com.example.healthyapp;

import android.app.Application;

import androidx.room.Room;

import com.example.healthyapp.data.HoursSleepDataBase;

public class ApplicationController extends Application {

    private static ApplicationController instance;
    private static HoursSleepDataBase hoursSleepDataBase;

    private final String hoursSleepDataBaseName = "HoursSleepDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        setUpDataBase();
    }

    private void setUpDataBase(){
        hoursSleepDataBase = Room.databaseBuilder(
                getApplicationContext(),
                HoursSleepDataBase.class,
                hoursSleepDataBaseName)
                .addMigrations(HoursSleepDataBase.MIGRATION_1_2)
                .build();
    }
    public static HoursSleepDataBase getHoursSleepDataBase(){ return hoursSleepDataBase;}
    public static ApplicationController getInstance(){ return  instance;}
}
