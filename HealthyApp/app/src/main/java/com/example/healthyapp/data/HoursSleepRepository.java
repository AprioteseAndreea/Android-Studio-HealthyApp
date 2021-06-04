package com.example.healthyapp.data;

import com.example.healthyapp.ApplicationController;
import com.example.healthyapp.data.tasks.DeleteHoursSleep;
import com.example.healthyapp.data.tasks.GetAllHoursSleep;
import com.example.healthyapp.data.tasks.InsertHoursSleep;
import com.example.healthyapp.models.dbEntities.HoursSleep;

import java.util.List;

public class HoursSleepRepository {
    public static interface OnSuccesListener {
        void onSuccess();
    }

    public static interface OnGetToDosListener {
        void onSuccess(List<HoursSleep> items);
    }

    private HoursSleepDataBase hoursSleepDataBase;

    public HoursSleepRepository() {
        hoursSleepDataBase = ApplicationController.getHoursSleepDataBase();
    }
    public void insertHoursSleep(HoursSleep toDoItem, OnSuccesListener listener) {
        new InsertHoursSleep(hoursSleepDataBase, listener).execute(toDoItem);
    }

    public void getAllHoursSleep(OnGetToDosListener listener) {
        new GetAllHoursSleep(hoursSleepDataBase, listener).execute();
    }
    public void deleteHoursSleep(HoursSleep toDoItem, OnSuccesListener listener) {
        new DeleteHoursSleep(hoursSleepDataBase, listener).execute(toDoItem);
    }
}
