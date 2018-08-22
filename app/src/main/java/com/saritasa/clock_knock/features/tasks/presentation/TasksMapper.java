package com.saritasa.clock_knock.features.tasks.presentation;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Mapper class for mapping data between presentation and domain layers.
 */
public class TasksMapper{

    /**
     * Maps domain objects into presentation objects with sorting by priority.
     *
     * @param aTaskDomainObservable Observable object with domain tasks.
     * @return List of tasks in a presentation data format (TasksAdapterItem).
     */
    public static Single<List<TasksAdapterItem>> mapDomainObjectsToPresentationObjects(Observable<TasksDomain> aTaskDomainObservable){
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
                .map(TasksMapper::mapDomainObjectToPresentationObject)
                .toList();
    }

    /**
     * Maps single domain object into presentation object.
     *
     * @param aTasksDomain Domain object for mapping.
     * @return Presentation object.
     */
    public static TasksAdapterItem mapDomainObjectToPresentationObject(TasksDomain aTasksDomain){
        return aTasksDomain.toTasksObject();
    }
}
