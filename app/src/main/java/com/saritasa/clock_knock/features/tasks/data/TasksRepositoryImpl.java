package com.saritasa.clock_knock.features.tasks.data;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Repository object of tasks feature.
 */
public class TasksRepositoryImpl extends BaseRepositoryImpl implements TasksRepository{

    RestApi mRestApi;

    public TasksRepositoryImpl(ResourceManager aResourceManager, RestApi aRestApi){
        super(aResourceManager);
        mRestApi = aRestApi;
    }

    @Override
    public Observable<TasksDomain> loadTasks(){
        Timber.d("Loading tasks");
        return
                //mRestApi.getTasks("assignee=maxim.kovalev")
                getTestApiTask()
                        .flatMapObservable(TasksEntityMapper::mapTasksEntityToDomainObjects);
    }

    /**
     * Test method. Hardcodes object to test feature without using api.
     * This method will be deprecated soon.
     *
     * @return Single object of imitated api response.
     */
    private Single<TasksResponseEntity> getTestApiTask(){

        return Single.create(emitter -> {
            TasksResponseEntity tasksResponseEntity = new TasksResponseEntity();

            TasksStatusEntity tasksStatusEntity = new TasksStatusEntity("IN PROGRESS");
            TasksPriorityEntity tasksPriorityEntity = new TasksPriorityEntity("Medium",
                                                                              "http://www.iconninja.com/files/704/5/100/home-icon.png",
                                                                              "3");
            TasksAvatarUrlsEntity tasksAvatarUrlsEntity = new TasksAvatarUrlsEntity("http://www.iconninja.com/files/704/5/100/home-icon.png");
            TasksProjectEntity tasksProjectEntity = new TasksProjectEntity("TestProj", tasksAvatarUrlsEntity);
            TasksFieldsEntity tasksFieldsEntity = new TasksFieldsEntity("Test task",
                                                                        "This is a task for testing methods.",
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
