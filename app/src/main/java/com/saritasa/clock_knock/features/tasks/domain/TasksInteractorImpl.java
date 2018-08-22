package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;
import com.saritasa.clock_knock.features.tasks.presentation.TasksAdapterItem;
import com.saritasa.clock_knock.features.tasks.presentation.TasksMapper;

import java.util.List;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Interactor for tasks module.
 */
public class TasksInteractorImpl extends BaseInteractorImpl<TasksRepository> implements TasksInteractor{

    public TasksInteractorImpl(TasksRepository aTasksRepository){
        super(aTasksRepository);
    }

    @Override
    public Single<List<TasksAdapterItem>> loadTasks(){
        Timber.d("Loading tasks");
        return TasksMapper.mapDomainObjectsToPresentationObjects(mRepository.loadTasks());
    }
}

