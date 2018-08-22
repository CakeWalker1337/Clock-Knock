package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksIssueEntity{

    @SerializedName("fields")
    private TasksFieldsEntity mFields;
    @SerializedName("key")
    private String mName;
    @SerializedName("id")
    private String mId;

    public TasksIssueEntity(TasksFieldsEntity aFields, String aName, String aId){
        mId = aId;
        mName = aName;
        mFields = aFields;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mFields, mName, mId);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksIssueEntity that = (TasksIssueEntity) aO;
        return Objects.equals(mFields, that.mFields) &&
                Objects.equals(mName, that.mName) &&
                Objects.equals(mId, that.mId);
    }

    @Override
    public String toString(){
        return "TasksResponseEntity{" +
                "mFields=" + mFields +
                ", mName='" + mName + '\'' +
                ", mId='" + mId + '\'' +
                '}';
    }

    public TasksFieldsEntity getFields(){
        return mFields;
    }

    public void setFields(TasksFieldsEntity aFields){
        mFields = aFields;
    }

    public String getName(){
        return mName;
    }

    public void setName(String aName){
        mName = aName;
    }

    public String getId(){
        return mId;
    }

    public void setId(String aId){
        mId = aId;
    }

    public TasksDomain toTaskDomain(){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setId(getId());
        tasksDomain.setName(getName());
        tasksDomain.setPriorityIconUrl(getFields().getPriority().getIconUrl());
        tasksDomain.setProjectAvatarUrl(getFields().getProject().getAvatarUrls().getLargeAvatarUrl());
        tasksDomain.setStatus(getFields().getStatus().getName());
        tasksDomain.setSummary(getFields().getSummary());
        tasksDomain.setPriorityId(Integer.parseInt(getFields().getPriority().getPriorityId()));
        return tasksDomain;
    }

}
