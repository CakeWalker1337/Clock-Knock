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
     * @param aTaskEntity issue object to map.
     * @return mapped TaskDomain object.
     */
    @NonNull
    public static TasksDomain mapEntityObjectToDomainObject(@NonNull TaskEntity aTaskEntity){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setId(aTaskEntity.getId());
        tasksDomain.setName(aTaskEntity.getTitle());
        tasksDomain.setStatus(aTaskEntity.getStatus());
        tasksDomain.setSummary(aTaskEntity.getDescription());
        tasksDomain.setPriorityId(aTaskEntity.getPriority());
        return tasksDomain;
    }

    @NonNull
    public static TaskEntity mapDomainObjectToEntityObject(@NonNull TasksDomain aTasksDomain){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(aTasksDomain.getId());
        taskEntity.setTitle(aTasksDomain.getName());
        taskEntity.setStatus(aTasksDomain.getStatus());
        taskEntity.setDescription(aTasksDomain.getSummary());
        taskEntity.setPriority(aTasksDomain.getPriorityId());
        return taskEntity;
    }
}
