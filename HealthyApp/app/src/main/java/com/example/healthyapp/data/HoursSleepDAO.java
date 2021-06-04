package com.example.healthyapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthyapp.models.dbEntities.HoursSleep;

import java.util.List;

@Dao
public interface HoursSleepDAO {
    @Query("SELECT * FROM hourssleep")
    List<HoursSleep> getAll();

    @Insert
    void insertHoursSleep(HoursSleep toDoItem);

    @Delete
    void delete(HoursSleep toDoItem);
}
