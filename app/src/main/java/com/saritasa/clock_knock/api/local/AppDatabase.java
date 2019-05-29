package com.saritasa.clock_knock.api.local;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.saritasa.clock_knock.api.local.task.TaskDao;
import com.saritasa.clock_knock.api.local.worklog.WorklogDao;
import com.saritasa.clock_knock.features.tasks.data.TaskEntity;
import com.saritasa.clock_knock.features.worklog.data.WorklogEntity;

@Database(entities = {TaskEntity.class, WorklogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract TaskDao getTaskDao();

    public abstract WorklogDao getWorklogDao();
}
