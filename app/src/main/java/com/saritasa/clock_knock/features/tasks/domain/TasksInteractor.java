package com.saritasa.clock_knock.features.tasks.domain;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Interface of interactor. Loads and maps tasks from API.
 */
public interface TasksInteractor{

    /**
     * Loads tasks from API and maps it into presentation format.
     *
     * @return Single object with list of presentation objects sorted by priority.
     */
    ArrayList<TasksDomain> loadTasks();

    String getStringResource(int aResourceId, Object... aParams);

    long createTask(TasksDomain aTasksDomain);

    int updateTask(TasksDomain aTasksDomain);

    int deleteTask(TasksDomain aTasksDomain);
}
