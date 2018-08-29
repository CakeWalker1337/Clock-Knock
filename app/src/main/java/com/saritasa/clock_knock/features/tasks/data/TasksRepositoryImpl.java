package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Repository object of tasks feature.
 */
public class TasksRepositoryImpl extends BaseRepositoryImpl implements TasksRepository{

    RestApi mRestApi;

    /**
     * @param aResourceManager - resource manager object
     * @param aRestApi - object of rest api
     */
    public TasksRepositoryImpl(@NonNull ResourceManager aResourceManager, @NonNull RestApi aRestApi){
        super(aResourceManager);
        mRestApi = aRestApi;
    }

    @NonNull
    @Override
    public Observable<TasksDomain> loadTasks(){
        return
//                mRestApi.getTasks("assignee=maxim.kovalev")
//                        .map(aTasksResponseEntityResponse -> {
//                            if(!aTasksResponseEntityResponse.isSuccessful()){
//                                throw new Exception("Error while fetching task list.");
//                            }
//                            return aTasksResponseEntityResponse.body().getIssues();
//                        })
                getTestApiTask()
                        .flatMapObservable(source -> Observable.fromIterable(source.getIssues()))
                        .map(TasksEntityMapper::mapEntityObjectToDomainObject);
    }

    /**
     * Test method. Hardcodes object to test feature without using api.
     * This method will be deprecated soon.
     *
     * @return Single object of imitated api response.
     */
    //TODO: Delete this one when login feature will be ready to merging.
    @NonNull
    private Single<TasksResponseEntity> getTestApiTask(){

        return Single.create(emitter -> {
            TasksResponseEntity tasksResponseEntity = new TasksResponseEntity();

            TasksStatusEntity tasksStatusEntity = new TasksStatusEntity("IN PROGRESS");
            TasksPriorityEntity tasksPriorityEntity = new TasksPriorityEntity("http://www.iconninja.com/files/704/5/100/home-icon.png",
                                                                              "3");
            TasksAvatarUrlsEntity tasksAvatarUrlsEntity = new TasksAvatarUrlsEntity("http://www.iconninja.com/files/704/5/100/home-icon.png");
            TasksProjectEntity tasksProjectEntity = new TasksProjectEntity(tasksAvatarUrlsEntity);
            TasksFieldsEntity tasksFieldsEntity = new TasksFieldsEntity("Test task",
                                                                        tasksStatusEntity,
                                                                        tasksPriorityEntity,
                                                                        tasksProjectEntity);

            TasksIssueEntity tasksIssueEntity = new TasksIssueEntity(tasksFieldsEntity, "Misc-208", "1");

            ArrayList<TasksIssueEntity> arrayList = new ArrayList<>();
            arrayList.add(tasksIssueEntity);

            tasksResponseEntity.setIssues(arrayList);

            emitter.onSuccess(tasksResponseEntity);
        });
    }
}
