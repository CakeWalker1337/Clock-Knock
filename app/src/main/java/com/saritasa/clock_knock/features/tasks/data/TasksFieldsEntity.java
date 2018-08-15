package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class TasksFieldsEntity{

    @SerializedName("status")
    private TasksStatusEntity mStatus;
    @SerializedName("priority")
    private TasksPriorityEntity mPriority;
    @SerializedName("project")
    private TasksProjectEntity mProject;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("summary")
    private String mSummary;

    @Override
    public int hashCode(){

        return Objects.hash(mStatus, mPriority, mProject, mDescription, mSummary);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksFieldsEntity that = (TasksFieldsEntity) aO;
        return Objects.equals(mStatus, that.mStatus) &&
                Objects.equals(mPriority, that.mPriority) &&
                Objects.equals(mProject, that.mProject) &&
                Objects.equals(mDescription, that.mDescription) &&
                Objects.equals(mSummary, that.mSummary);
    }

    @Override
    public String toString(){
        return "TasksFieldsEntity{" +
                "mStatus=" + mStatus +
                ", mPriority=" + mPriority +
                ", mProject=" + mProject +
                ", mDescription='" + mDescription + '\'' +
                ", mSummary='" + mSummary + '\'' +
                '}';
    }

    public TasksStatusEntity getStatus(){
        return mStatus;
    }

    public void setStatus(TasksStatusEntity aStatus){
        mStatus = aStatus;
    }

    public TasksPriorityEntity getPriority(){
        return mPriority;
    }

    public void setPriority(TasksPriorityEntity aPriority){
        mPriority = aPriority;
    }

    public TasksProjectEntity getProject(){
        return mProject;
    }

    public void setProject(TasksProjectEntity aProject){
        mProject = aProject;
    }

    public String getDescription(){
        return mDescription;
    }

    public void setDescription(String aDescription){
        mDescription = aDescription;
    }

    public String getSummary(){
        return mSummary;
    }

    public void setSummary(String aSummary){
        mSummary = aSummary;
    }
}
