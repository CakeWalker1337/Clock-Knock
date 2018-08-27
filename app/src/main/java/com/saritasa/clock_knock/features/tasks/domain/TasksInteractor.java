package com.saritasa.clock_knock.features.tasks.domain;

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
    Observable<TasksDomain> loadTasks();

    String getStringResource(int aResourceId, Object... aParams);

}
