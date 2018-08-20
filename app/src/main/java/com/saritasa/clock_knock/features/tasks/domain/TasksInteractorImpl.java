package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;

public class TasksInteractorImpl extends BaseInteractorImpl<TasksRepository> implements TasksInteractor{

    public TasksInteractorImpl(TasksRepository aTasksRepository){
        super(aTasksRepository);
    }

}

