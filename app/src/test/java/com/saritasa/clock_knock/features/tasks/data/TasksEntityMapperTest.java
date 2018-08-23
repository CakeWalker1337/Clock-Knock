package com.saritasa.clock_knock.features.tasks.data;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Task entity mapper test class
 */
public class TasksEntityMapperTest{

    /**
     * Tests mapping tasks entity to tasks domain objects.
     */
    @Test
    public void mapTasksEntityToDomainObjects_isMappingCorrect(){
        Single<TasksResponseEntity> tasksResponseEntitySingle = getTestTasksResponseEntity();
        Observable<TasksDomain> domainObservable = tasksResponseEntitySingle.flatMapObservable(TasksEntityMapper::mapTasksEntityToDomainObjects);
        Single<Boolean> result = domainObservable.contains(getTestTaskDomain());
        result.subscribe((Consumer<Boolean>) Assert::assertTrue);
    }

    /**
     * Tests mapping single entity object to domain object.
     */
    @Test
    public void mapEntityObjectToDomainObject_isMappingCorrect(){
        getTestTasksResponseEntity().map(aTasksResponseEntity -> {
            TasksIssueEntity issueEntity = aTasksResponseEntity.getIssues().get(0);
            return TasksEntityMapper.mapEntityObjectToDomainObject(issueEntity);
        })
                .subscribe(aTasksDomain -> assertEquals(getTestTaskDomain(), aTasksDomain));
    }

    /**
     * Creates domain object with hardcoded params.
     *
     * @return Tasks domain object
     */
    private TasksDomain getTestTaskDomain(){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setPriorityId(3);
        tasksDomain.setName("Misc-208");
        tasksDomain.setStatus("IN PROGRESS");
        tasksDomain.setId("1");
        tasksDomain.setPriorityIconUrl("http://www.iconninja.com/files/704/5/100/home-icon.png");
        tasksDomain.setProjectAvatarUrl("http://www.iconninja.com/files/704/5/100/home-icon.png");
        tasksDomain.setSummary("Test task");
        return tasksDomain;
    }

    /**
     * Creates Response entity object with hardcoded params.
     *
     * @return Response entity object included in Single RxJava Class
     */
    private Single<TasksResponseEntity> getTestTasksResponseEntity(){

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