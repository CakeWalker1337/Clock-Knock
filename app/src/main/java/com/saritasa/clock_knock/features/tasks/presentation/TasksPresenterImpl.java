package com.saritasa.clock_knock.features.tasks.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

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

    @Override
    public void loadTasks(){

        mTasksInteractor.loadTasks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TasksObject>>(){

                    @Override
                    public void onSubscribe(final Disposable d){

                    }

                    @Override
                    public void onSuccess(final List<TasksObject> aTasksObjects){
                        getViewState().updateView(aTasksObjects);
                    }

                    @Override
                    public void onError(final Throwable e){

                    }
                });

    }

}
