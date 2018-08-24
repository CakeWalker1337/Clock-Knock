package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Task entity mapper test class
 */
public class TasksEntityMapperTest{
    /**
     * Tests mapping single entity object to domain object.
     */
    @Test
    public void mapEntityObjectToDomainObject_isMappingCorrect(){
        TasksDomain tasksDomain = TasksEntityMapper.mapEntityObjectToDomainObject(getTestTasksIssueEntity());
        assertEquals(getTestTaskDomain(), tasksDomain);
    }

    /**
     * Creates domain object with hardcoded params.
     *
     * @return Tasks domain object
     */
    @NonNull
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
    @NonNull
    private TasksIssueEntity getTestTasksIssueEntity(){

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
        return tasksIssueEntity;
    }
}