package com.saritasa.clock_knock.features.tasks.data;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import io.reactivex.Observable;

public class TasksRepositoryImpl extends BaseRepositoryImpl implements TasksRepository{

    RestApi mRestApi;

    public TasksRepositoryImpl(ResourceManager aResourceManager, RestApi aRestApi){
        super(aResourceManager);
        mRestApi = aRestApi;
    }

    public Observable<TasksDomain> loadTasks(){
        return mRestApi.getTasks("assignee=maxim.kovalev")
                .flatMapObservable(TasksDataMapper::mapTasksEntityToDomain);
    }

}
