package com.saritasa.clock_knock.features.tasks.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Interactor for tasks module. Processes data in the domain layer.
 */
public class TasksInteractorImpl extends BaseInteractorImpl<TasksRepository> implements TasksInteractor{

    public static final String PROGRESS_STATUS_DONE = "Done";
    public static final int LESS = -1;
    public static final int MORE = 1;
    public static final int EQUAL = 0;

    /**
     * @param aTasksRepository - repository object.
     */
    public TasksInteractorImpl(@NonNull TasksRepository aTasksRepository){
        super(aTasksRepository);
    }

    @Override
    @NonNull
    public ArrayList<TasksDomain> loadTasks(){
        ArrayList<TasksDomain> tasksDomains = mRepository.loadTasks();
        Collections.sort(tasksDomains,
                         (first, second) -> {
                             if(first.getPriorityId() < second.getPriorityId()){
                                 return LESS;
                             } else if(first.getPriorityId() == second.getPriorityId()){
                                 return EQUAL;
                             } else{
                                 return MORE;
                             }
                         });
        return tasksDomains;
    }

    @Override
    public long createTask(final TasksDomain aTasksDomain){
        return mRepository.createTask(aTasksDomain);
    }

    @Override
    public int updateTask(final TasksDomain aTasksDomain){
        return mRepository.updateTask(aTasksDomain);
    }

    @Override
    public int deleteTask(final TasksDomain aTasksDomain){
        return mRepository.deleteTask(aTasksDomain);
    }

    public String getStringResource(int aResourceId){
        return mRepository.getStringResource(aResourceId);
    }

}

