package com.saritasa.clock_knock.features.tasks.data;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import io.reactivex.Observable;

/**
 * Mapper class for mapping data between entity and domain layers.
 */
public class TasksEntityMapper{

    /**
     * Maps API response into domain objects.
     *
     * @param aEntity API response object.
     * @return Observable with domain objects.
     */
    public static Observable<TasksDomain> mapTasksEntityToDomainObjects(TasksResponseEntity aEntity){
        Observable<TasksDomain> observable = Observable.create(emitter -> {
            for(TasksIssueEntity issue : aEntity.getIssues()){
                emitter.onNext(mapEntityObjectToDomainObject(issue));
            }
            emitter.onComplete();
        });
        return observable
                .filter(aTasksDomain -> !aTasksDomain.getStatus().equals("Done"));
    }

    /**
     * Maps single issue object into tasks domain object.
     *
     * @param aTasksIssueEntity issue object to map.
     * @return mapped TaskDomain object.
     */
    public static TasksDomain mapEntityObjectToDomainObject(TasksIssueEntity aTasksIssueEntity){
        return aTasksIssueEntity.toTaskDomain();
    }

}
