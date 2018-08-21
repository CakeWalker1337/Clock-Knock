package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;
import com.saritasa.clock_knock.features.tasks.presentation.TasksObject;

import java.util.List;

import io.reactivex.Single;

public class TasksInteractorImpl extends BaseInteractorImpl<TasksRepository> implements TasksInteractor{

    public TasksInteractorImpl(TasksRepository aTasksRepository){
        super(aTasksRepository);
    }

    @Override
    public Single<List<TasksObject>> loadTasks(){
        return TasksDomainMapper.mapDomainObjectsToPresentationObjects(mRepository.loadTasks());
    }
}

