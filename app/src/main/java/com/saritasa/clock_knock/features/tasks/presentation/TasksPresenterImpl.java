package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Presenter for Tasks module.
 */
@InjectViewState
public class TasksPresenterImpl extends BasePresenterImpl<TasksView> implements TasksPresenter<TasksView>{

    private TasksInteractor mTasksInteractor;

    /**
     * @param aTasksInteractor - interactor object.
     */
    public TasksPresenterImpl(@NonNull TasksInteractor aTasksInteractor){
        mTasksInteractor = aTasksInteractor;
    }

    @Override
    public void onRequest(){

        Timber.d("Loading tasks");

        getViewState().showLoadingProgress();

        Disposable disposable = mTasksInteractor.loadTasks()
                .map(TasksMapper::mapTasksDomainToTasksAdapterItem)
                .toList()
                .doFinally(() -> getViewState().hideLoadingProgress())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess,
                           this::handleError);
        unsubscribeOnDestroy(disposable);
    }

    /**
     * Updates recyclerview items. Calls only on success.
     *
     * @param aTasksAdapterItems new list of data
     */
    private void handleSuccess(List<TasksAdapterItem> aTasksAdapterItems){
        if(aTasksAdapterItems.isEmpty()){
            getViewState().showNoTasksMessageView();
        }
        Timber.d("Task was loaded");
        getViewState().updateTaskList(aTasksAdapterItems);
    }

    /**
     * Shows information about error. Calls only on failure.
     *
     * @param aThrowable exception.
     */
    private void handleError(Throwable aThrowable){
        Timber.e(aThrowable);
        getViewState().showErrorMessage(aThrowable.getMessage());
    }
}
