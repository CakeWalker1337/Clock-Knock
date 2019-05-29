package com.saritasa.clock_knock.api.local.worklog;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.saritasa.clock_knock.features.worklog.data.WorklogEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface WorklogDao{

    @Query("SELECT * FROM worklogs WHERE task_id = :taskId")
    List<WorklogEntity> getAllWorklogsByTaskId(long taskId);

    @Insert
    long createWorklog(WorklogEntity aWorklogEntity);

    @Update
    int updateWorklog(WorklogEntity aWorklogEntity);

    @Delete
    int deleteWorklog(WorklogEntity aWorklogEntity);
}
