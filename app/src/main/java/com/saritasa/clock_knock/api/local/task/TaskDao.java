package com.saritasa.clock_knock.api.local.task;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.saritasa.clock_knock.features.tasks.data.TaskEntity;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface TaskDao{

    @Query("SELECT * from tasks")
    Observable<TaskEntity> getAllTasks();


}
