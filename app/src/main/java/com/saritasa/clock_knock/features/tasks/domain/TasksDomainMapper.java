package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.presentation.TasksObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class TasksDomainMapper{

    public static Single<List<TasksObject>> mapDomainObjectsToPresentationObjects(Observable<TasksDomain> aTaskDomainObservable){
        return aTaskDomainObservable
                .sorted((aTasksDomain1, aTasksDomain2) -> {
                    if(aTasksDomain1.getPriorityId() < aTasksDomain2.getPriorityId()){
                        return 1;

                    } else if(aTasksDomain1.getPriorityId() == aTasksDomain2.getPriorityId()){
                        return 0;
                    } else{
                        return -1;
                    }
                })
                .map(TasksDomainMapper::mapDomainObjectToPresentationObject)
                .toList();
    }

    public static TasksObject mapDomainObjectToPresentationObject(TasksDomain aTasksDomain){
        return aTasksDomain.toTasksObject();
    }
}
