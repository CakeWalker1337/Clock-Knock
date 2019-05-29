package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

/**
 * Mapper class for mapping data between presentation and domain layers.
 */
public class TasksMapper{

    /**
     * Maps single domain object into presentation object.
     *
     * @param aTasksDomain Domain object for mapping.
     * @return Presentation object.
     */
    @NonNull
    public static TasksAdapterItem mapTasksDomainToTasksAdapterItem(@NonNull TasksDomain aTasksDomain){
        TasksAdapterItem tasksAdapterItem = new TasksAdapterItem();
        tasksAdapterItem.setId(aTasksDomain.getId());
        tasksAdapterItem.setName(aTasksDomain.getName());
        tasksAdapterItem.setStatus(aTasksDomain.getStatus());
        tasksAdapterItem.setSummary(aTasksDomain.getSummary());
        tasksAdapterItem.setPriority(aTasksDomain.getPriorityId());
        return tasksAdapterItem;
    }

    public static TasksDomain mapTasksAdapterItemToTasksDomain(@NonNull TasksAdapterItem aTasksAdapterItem){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setId(aTasksAdapterItem.getId());
        tasksDomain.setName(aTasksAdapterItem.getName());
        tasksDomain.setStatus(aTasksAdapterItem.getStatus());
        tasksDomain.setSummary(aTasksAdapterItem.getSummary());
        tasksDomain.setPriorityId(aTasksAdapterItem.getPriority());
        return tasksDomain;
    }

}
