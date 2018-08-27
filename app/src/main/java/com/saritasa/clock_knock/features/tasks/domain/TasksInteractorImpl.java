package com.saritasa.clock_knock.features.tasks.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;

import io.reactivex.Observable;

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
    public Observable<TasksDomain> loadTasks(){
        return mRepository.loadTasks()
                .filter(aTasksDomain -> !aTasksDomain.getStatus().equals(PROGRESS_STATUS_DONE))
                .sorted((aTasksDomain1, aTasksDomain2) -> {
                    if(aTasksDomain1.getPriorityId() < aTasksDomain2.getPriorityId()){
                        return LESS;
                    } else if(aTasksDomain1.getPriorityId() == aTasksDomain2.getPriorityId()){
                        return EQUAL;
                    } else{
                        return MORE;
                    }
                });
    }

    public String getStringResource(int aResourceId){
        return mRepository.getStringResource(aResourceId);
    }

}

