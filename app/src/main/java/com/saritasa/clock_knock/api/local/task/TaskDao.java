package com.saritasa.clock_knock.api.local.task;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.saritasa.clock_knock.features.tasks.data.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao{

    @Query("SELECT * from tasks")
    List<TaskEntity> getAllTasks();

    @Insert
    long addTask(TaskEntity aTaskEntity);

    @Update
    int updateTask(TaskEntity aTaskEntity);

    @Delete
    int deleteTask(TaskEntity aTaskEntity);
}
