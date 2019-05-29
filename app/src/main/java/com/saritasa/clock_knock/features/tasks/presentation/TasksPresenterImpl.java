package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;
import com.saritasa.clock_knock.util.Constants;

import org.mockito.internal.matchers.And;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {

            ArrayList<TasksDomain> tasksDomains = mTasksInteractor.loadTasks();
            ArrayList<TasksAdapterItem> tasksAdapterItems = new ArrayList<>();
            for(TasksDomain tasksDomain : tasksDomains){
                tasksAdapterItems.add(TasksMapper.mapTasksDomainToTasksAdapterItem(tasksDomain));
            }
            tasksDomains.clear();

            getViewState().runTaskOnUiThread(() -> handleSuccess(tasksAdapterItems));
        });
    }

    @Override
    public void onTaskAdd(String aTitle, String aSummary, String aStatus, int aPriority){
        if(validateTaskData(aTitle, aSummary, aStatus, aPriority)){
            TasksAdapterItem item = new TasksAdapterItem();
            item.setName(aTitle);
            item.setSummary(aSummary);
            item.setStatus(aStatus);
            item.setPriority(aPriority);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                long id = mTasksInteractor.createTask(TasksMapper.mapTasksAdapterItemToTasksDomain(item));

                getViewState().runTaskOnUiThread(() -> {
                    item.setId(id);
                    getViewState().addTaskToList(item);
                });
            });
        }
    }

    @Override
    public void onTaskEdit(int aPosition, long aId, String aTitle, String aSummary, String aStatus, int aPriority){
        if(validateTaskData(aTitle, aSummary, aStatus, aPriority)){
            TasksAdapterItem item = new TasksAdapterItem();
            item.setName(aTitle);
            item.setSummary(aSummary);
            item.setStatus(aStatus);
            item.setPriority(aPriority);
            item.setId(aId);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                int result = mTasksInteractor.updateTask(TasksMapper.mapTasksAdapterItemToTasksDomain(item));

                getViewState().runTaskOnUiThread(() -> {
                    if(result == 0){
                        getViewState().showErrorMessage("Error in task editing!");
                        return;
                    }
                    getViewState().editTaskInList(item, aPosition);
                });

            });

        }
    }

    @Override
    public void onTaskDelete(int aPosition, TasksAdapterItem aTasksAdapterItem){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            int result = mTasksInteractor.deleteTask(TasksMapper.mapTasksAdapterItemToTasksDomain(aTasksAdapterItem));
            getViewState().runTaskOnUiThread(() -> {
                if(result == 0){
                    getViewState().showErrorMessage("Error in task deleting!");
                    return;
                }
                getViewState().removeTaskFromList(aPosition);
            });

        });
    }

    /**
     * Updates recyclerview items. Calls only on success.
     *
     * @param aTasksAdapterItems new list of data
     */
    private void handleSuccess(List<TasksAdapterItem> aTasksAdapterItems){
        if(aTasksAdapterItems.isEmpty()){
            getViewState().showNoTasksMessageView();
            getViewState().hideTasksView();
        } else{
            getViewState().hideNoTasksMessageView();
            getViewState().showTasksView();
        }
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

    private boolean validateTaskData(String aTitle, String aSummary, String aStatus, int aPriority){

        if(aTitle == null || aTitle.equals("")){
            getViewState().showErrorMessage("Invalid title!");
            return false;
        } else if(aSummary == null || aSummary.equals("")){
            getViewState().showErrorMessage("Invalid description!");
            return false;
        } else if(aStatus == null || aStatus.equals("")){
            getViewState().showErrorMessage("Invalid status!");
            return false;
        } else if(aPriority == Constants.PRIORITY_INVALID){
            getViewState().showErrorMessage("Invalid priority!");
            return false;
        }
        return true;
    }
}
