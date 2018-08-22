package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.presentation.TasksAdapterItem;

import java.util.List;

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
    Single<List<TasksAdapterItem>> loadTasks();

}
