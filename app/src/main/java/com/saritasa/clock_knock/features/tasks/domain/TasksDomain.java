package com.saritasa.clock_knock.features.tasks.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Data class for domain layer. Contains priorityId using for sorting list of this type of objects.
 */
public class TasksDomain{

    private String mName;
    private String mId;
    private String mStatus;
    private String mSummary;
    private int mPriorityId;

    @NonNull
    public int getPriorityId(){
        return mPriorityId;
    }

    public void setPriorityId(@NonNull final int aPriorityId){
        mPriorityId = aPriorityId;
    }

    @NonNull
    public String getName(){
        return mName;
    }

    public void setName(@NonNull final String aName){
        mName = aName;
    }

    @NonNull
    public String getId(){
        return mId;
    }

    public void setId(@NonNull final String aId){
        mId = aId;
    }

    @NonNull
    public String getStatus(){
        return mStatus;
    }

    public void setStatus(@NonNull final String aStatus){
        mStatus = aStatus;
    }

    @NonNull
    public String getSummary(){
        return mSummary;
    }

    public void setSummary(@NonNull final String aSummary){
        mSummary = aSummary;
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(!(aObject instanceof TasksDomain)){
            return false;
        }
        TasksDomain that = (TasksDomain) aObject;
        return getPriorityId() == that.getPriorityId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getSummary(), that.getSummary());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getId(), getStatus(), getSummary(), getPriorityId());
    }

    @Override
    public String toString(){
        return "TasksDomain{" +
                "mName='" + mName + '\'' +
                ", mId='" + mId + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mSummary='" + mSummary + '\'' +
                ", mPriorityId=" + mPriorityId +
                '}';
    }
}
