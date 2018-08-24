package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.presentation.TasksAdapterItem;

import io.reactivex.Observable;

/**
 * Interface of interactor. Loads and maps tasks from API.
 */
public interface TasksInteractor{

    /**
     * Loads tasks from API and maps it into presentation format.
     *
     * @return Single object with list of presentation objects sorted by priority.
     */
    Observable<TasksAdapterItem> loadTasks();

}
