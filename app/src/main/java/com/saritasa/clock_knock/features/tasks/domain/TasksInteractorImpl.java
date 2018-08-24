package com.saritasa.clock_knock.features.tasks.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;
import com.saritasa.clock_knock.features.tasks.presentation.TasksAdapterItem;
import com.saritasa.clock_knock.features.tasks.presentation.TasksMapper;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Interactor for tasks module.
 */
public class TasksInteractorImpl extends BaseInteractorImpl<TasksRepository> implements TasksInteractor{

    /**
     * Constructs TasksInteractorImpl object with params.
     *
     * @param aTasksRepository - repository object.
     */
    public TasksInteractorImpl(@NonNull TasksRepository aTasksRepository){
        super(aTasksRepository);
    }

    @Override
    @NonNull
    public Observable<TasksAdapterItem> loadTasks(){
        Timber.d("Loading tasks");
        return mRepository.loadTasks()
                .sorted((aTasksDomain1, aTasksDomain2) -> {
                    if(aTasksDomain1.getPriorityId() < aTasksDomain2.getPriorityId()){
                        return -1;

                    } else if(aTasksDomain1.getPriorityId() == aTasksDomain2.getPriorityId()){
                        return 0;
                    } else{
                        return 1;
                    }
                })
                .map(TasksMapper::mapDomainObjectToPresentationObject);
    }

}

