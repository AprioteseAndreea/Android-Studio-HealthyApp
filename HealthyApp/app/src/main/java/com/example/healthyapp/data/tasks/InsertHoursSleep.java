package com.example.healthyapp.data.tasks;

import android.os.AsyncTask;

import com.example.healthyapp.data.HoursSleepDataBase;
import com.example.healthyapp.data.HoursSleepRepository;
import com.example.healthyapp.models.dbEntities.HoursSleep;

public class InsertHoursSleep extends AsyncTask<HoursSleep, Void, Void> {

    private HoursSleepDataBase hoursSleepDataBase;
    private HoursSleepRepository.OnSuccesListener listener;

    public InsertHoursSleep(HoursSleepDataBase hoursSleepDataBase, HoursSleepRepository.OnSuccesListener listener) {
        this.hoursSleepDataBase = hoursSleepDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(HoursSleep... hoursSleeps) {
       hoursSleepDataBase.hoursSleepDAO().insertHoursSleep(hoursSleeps[0]);
       return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
