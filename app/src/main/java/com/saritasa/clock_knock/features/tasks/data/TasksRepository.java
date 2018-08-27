package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import io.reactivex.Observable;

/**
 * Interface for Repository of tasks feature.
 */
public interface TasksRepository extends BaseRepository{

    /**
     * Loads tasks from API and maps it into domain objects.
     * Composites a POJO objects into single domain objects object.
     *
     * @return Observable with domain objects.
     */
    @NonNull
    Observable<TasksDomain> loadTasks();

}
