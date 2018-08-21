package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.Objects;

public class TasksIssueEntity{

    @SerializedName("fields")
    private TasksFieldsEntity mFields;
    @SerializedName("key")
    private String mKey;
    @SerializedName("id")
    private String mId;

    @Override
    public int hashCode(){

        return Objects.hash(mFields, mKey, mId);
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
                Objects.equals(mKey, that.mKey) &&
                Objects.equals(mId, that.mId);
    }

    @Override
    public String toString(){
        return "TasksResponseEntity{" +
                "mFields=" + mFields +
                ", mKey='" + mKey + '\'' +
                ", mId='" + mId + '\'' +
                '}';
    }

    public TasksFieldsEntity getFields(){
        return mFields;
    }

    public void setFields(TasksFieldsEntity aFields){
        mFields = aFields;
    }

    public String getKey(){
        return mKey;
    }

    public void setKey(String aKey){
        mKey = aKey;
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
        tasksDomain.setKey(getKey());
        tasksDomain.setPriorityIconUrl(getFields().getPriority().getIconurl());
        tasksDomain.setProjectAvatarUrl(getFields().getProject().getAvatarurls().getLargeAvatarUrl());
        tasksDomain.setStatus(getFields().getStatus().getName());
        tasksDomain.setSummary(getFields().getSummary());
        tasksDomain.setPriorityId(Integer.parseInt(getFields().getPriority().getPriorityId()));
        return tasksDomain;
    }

}
