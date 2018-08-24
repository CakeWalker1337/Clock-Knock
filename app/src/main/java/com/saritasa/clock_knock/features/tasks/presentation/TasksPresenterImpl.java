package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Presenter for Tasks module.
 */
@InjectViewState
public class TasksPresenterImpl extends BasePresenter<TasksView> implements TasksPresenter<TasksView>{

    private TasksInteractor mTasksInteractor;

    /**
     * Constructs TasksPresenterImpl object with params.
     *
     * @param aTasksInteractor - interactor object.
     */
    public TasksPresenterImpl(@NonNull TasksInteractor aTasksInteractor){
        mTasksInteractor = aTasksInteractor;
    }

    @Override
    public void attachView(@NonNull final TasksView view){
        super.attachView(view);
    }

    @Override
    public void detachView(@NonNull final TasksView view){
        super.detachView(view);
    }

    @Override
    public void loadTasks(){

        Timber.d("Loading tasks");
        mTasksInteractor.loadTasks()
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(aTasksAdapterItems -> {
                               Timber.d("Task was loaded");
                               getViewState().updateView(aTasksAdapterItems);
                           },
                           Timber::e);

    }

}
