package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.presentation.TasksObject;

import java.util.List;

import io.reactivex.Single;

public interface TasksInteractor{

    Single<List<TasksObject>> loadTasks();

}
