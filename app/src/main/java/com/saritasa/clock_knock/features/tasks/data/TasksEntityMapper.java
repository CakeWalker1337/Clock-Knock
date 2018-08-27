package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

/**
 * Mapper class for mapping data between entity and domain layers.
 */
public final class TasksEntityMapper{

    private TasksEntityMapper(){
    }

    /**
     * Maps single issue object into tasks domain object.
     *
     * @param aTasksIssueEntity issue object to map.
     * @return mapped TaskDomain object.
     */
    @NonNull
    public static TasksDomain mapEntityObjectToDomainObject(@NonNull TasksIssueEntity aTasksIssueEntity){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setId(aTasksIssueEntity.getId());
        tasksDomain.setName(aTasksIssueEntity.getName());
        tasksDomain.setPriorityIconUrl(aTasksIssueEntity.getFields().getPriority().getIconUrl());
        tasksDomain.setProjectAvatarUrl(aTasksIssueEntity.getFields().getProject().getAvatarUrls().getLargeAvatarUrl());
        tasksDomain.setStatus(aTasksIssueEntity.getFields().getStatus().getName());
        tasksDomain.setSummary(aTasksIssueEntity.getFields().getSummary());
        tasksDomain.setPriorityId(Integer.parseInt(aTasksIssueEntity.getFields().getPriority().getPriorityId()));
        return tasksDomain;
    }

}
