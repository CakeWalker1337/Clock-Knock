package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksFieldsEntity{

    @SerializedName("status")
    private TasksStatusEntity mStatus;
    @SerializedName("priority")
    private TasksPriorityEntity mPriority;
    @SerializedName("project")
    private TasksProjectEntity mProject;
    @SerializedName("summary")
    private String mSummary;

    /**
     * Constructs TasksFieldsEntity object with params.
     *
     * @param aSummary - Short description of task.
     * @param aStatus - status entity.
     * @param aPriority - priority entity.
     * @param aProject - project entity.
     */
    public TasksFieldsEntity(@NonNull String aSummary,
                             @NonNull TasksStatusEntity aStatus,
                             @NonNull TasksPriorityEntity aPriority,
                             @NonNull TasksProjectEntity aProject){
        mSummary = aSummary;
        mStatus = aStatus;
        mPriority = aPriority;
        mProject = aProject;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mStatus, mPriority, mProject, mSummary);
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        TasksFieldsEntity that = (TasksFieldsEntity) aObject;
        return Objects.equals(mStatus, that.mStatus) &&
                Objects.equals(mPriority, that.mPriority) &&
                Objects.equals(mProject, that.mProject) &&
                Objects.equals(mSummary, that.mSummary);
    }

    @Override
    public String toString(){
        return "TasksFieldsEntity{" +
                "mStatus=" + mStatus +
                ", mPriority=" + mPriority +
                ", mProject=" + mProject +
                ", mSummary='" + mSummary + '\'' +
                '}';
    }

    @NonNull
    public TasksStatusEntity getStatus(){
        return mStatus;
    }

    public void setStatus(@NonNull TasksStatusEntity aStatus){
        mStatus = aStatus;
    }

    @NonNull
    public TasksPriorityEntity getPriority(){
        return mPriority;
    }

    public void setPriority(@NonNull TasksPriorityEntity aPriority){
        mPriority = aPriority;
    }

    @NonNull
    public TasksProjectEntity getProject(){
        return mProject;
    }

    public void setProject(@NonNull TasksProjectEntity aProject){
        mProject = aProject;
    }

    @NonNull
    public String getSummary(){
        return mSummary;
    }

    public void setSummary(@NonNull String aSummary){
        mSummary = aSummary;
    }
}
