package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tasks mapper test class.
 */
public class TasksMapperTest{


    /**
     * Tests mapping domain object to presentation object. Results before and after launching will be equal.
     */
    @Test
    public void mapDomainObjectToPresentationObject_isObjectsEquals(){
        TasksDomain domainObject = createTasksDomain();
        TasksAdapterItem mappedObject = TasksMapper.mapDomainObjectToPresentationObject(domainObject);
        TasksAdapterItem tasksAdapterItem = createTasksAdapterItem();

        assertEquals(tasksAdapterItem, mappedObject);
    }

    /**
     * Creates tasks domain object.
     * @return Domain object.
     */
    @NonNull
    private TasksDomain createTasksDomain(){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setPriorityId(1);
        tasksDomain.setName("name1");
        tasksDomain.setStatus("IN PROGRESS");
        tasksDomain.setId("1");
        tasksDomain.setPriorityIconUrl("https://nothing.com/");
        tasksDomain.setProjectAvatarUrl("https://nothing.com/");
        tasksDomain.setSummary("Test tasks domain");
        return tasksDomain;
    }

    /**
     * Creates tasks domain object.
     *
     * @return Domain object.
     */
    @NonNull
    private TasksAdapterItem createTasksAdapterItem(){
        TasksAdapterItem tasksAdapterItem = new TasksAdapterItem();
        tasksAdapterItem.setName("name1");
        tasksAdapterItem.setStatus("IN PROGRESS");
        tasksAdapterItem.setId("1");
        tasksAdapterItem.setPriorityIconUrl("https://nothing.com/");
        tasksAdapterItem.setProjectAvatarUrl("https://nothing.com/");
        tasksAdapterItem.setSummary("Test tasks domain");
        return tasksAdapterItem;
    }
}