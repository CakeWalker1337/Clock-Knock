package com.saritasa.clock_knock.api.local.worklog;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.saritasa.clock_knock.features.worklog.data.WorklogEntity;

import io.reactivex.Observable;

@Dao
public interface WorklogDao{

    @Query("SELECT * FROM worklogs WHERE task_id = :taskId")
    Observable<WorklogEntity> getAllWorklogsByTaskId(String taskId);
}
