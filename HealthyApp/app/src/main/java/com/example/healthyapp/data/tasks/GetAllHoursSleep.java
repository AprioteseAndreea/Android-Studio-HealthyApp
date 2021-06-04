package com.example.healthyapp.data.tasks;

import android.os.AsyncTask;

import com.example.healthyapp.data.HoursSleepDataBase;
import com.example.healthyapp.data.HoursSleepRepository;
import com.example.healthyapp.models.dbEntities.HoursSleep;

import java.util.List;

public class GetAllHoursSleep extends AsyncTask<Void, Void, List<HoursSleep>> {

    private HoursSleepDataBase hoursSleepDataBase;
    private HoursSleepRepository.OnGetToDosListener listener;

    public GetAllHoursSleep(HoursSleepDataBase hoursSleepDataBase, HoursSleepRepository.OnGetToDosListener listener) {
        this.hoursSleepDataBase = hoursSleepDataBase;
        this.listener = listener;
    }

    @Override
    protected List<HoursSleep> doInBackground(Void... voids) {
       return hoursSleepDataBase.hoursSleepDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<HoursSleep> hoursSleeps) {
        super.onPostExecute(hoursSleeps);
        listener.onSuccess(hoursSleeps);
    }
}
