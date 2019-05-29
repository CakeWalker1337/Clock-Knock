package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

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
    ArrayList<TasksDomain> loadTasks();

    long createTask(TasksDomain aTasksDomain);

    int updateTask(TasksDomain aTasksDomain);

    int deleteTask(TasksDomain aTasksDomain);
}
