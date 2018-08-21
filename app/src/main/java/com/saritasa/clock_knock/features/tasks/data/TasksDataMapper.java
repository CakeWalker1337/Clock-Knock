package com.saritasa.clock_knock.features.tasks.data;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import io.reactivex.Observable;

public class TasksDataMapper{

    public static Observable<TasksDomain> mapTasksEntityToDomain(TasksResponseEntity aEntity){
        Observable<TasksDomain> observable = Observable.create(emitter -> {
            for(TasksIssueEntity issue : aEntity.getIssues()){
                emitter.onNext(issue.toTaskDomain());
            }
            emitter.onComplete();
        });
        return observable
                .filter(aTasksDomain -> !aTasksDomain.getStatus().equals("Done"));
    }

}
