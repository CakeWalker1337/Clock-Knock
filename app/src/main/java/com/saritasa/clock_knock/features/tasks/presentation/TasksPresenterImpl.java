package com.saritasa.clock_knock.features.tasks.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;

@InjectViewState
public class TasksPresenterImpl extends BasePresenter<TasksView> implements TasksPresenter<TasksView>{

    private TasksInteractor mTasksInteractor;

    public TasksPresenterImpl(TasksInteractor aTasksInteractor){
        mTasksInteractor = aTasksInteractor;
    }

    @Override
    public void attachView(final TasksView view){
        super.attachView(view);
    }

    @Override
    public void detachView(final TasksView view){
        super.detachView(view);
    }

}
