package com.saritasa.clock_knock.features.tasks.domain;

import android.support.annotation.NonNull;
import java.util.Objects;

/**
 * Data class for domain layer. Contains priorityId using for sorting list of this type of objects.
 */
public class TasksDomain{

    private String mName;
    private long mId;
    private String mStatus;
    private String mSummary;
    private int mPriorityId;

    public int getPriorityId(){
        return mPriorityId;
    }

    public void setPriorityId(final int aPriorityId){
        mPriorityId = aPriorityId;
    }

    @NonNull
    public String getName(){
        return mName;
    }

    public void setName(@NonNull final String aName){
        mName = aName;
    }

    public long getId(){
        return mId;
    }

    public void setId(final long aId){
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
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(!(aO instanceof TasksDomain)){
            return false;
        }
        TasksDomain that = (TasksDomain) aO;
        return getId() == that.getId() &&
                getPriorityId() == that.getPriorityId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getSummary(), that.getSummary());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getId(), getStatus(), getSummary(), getPriorityId());
    }

    @NonNull
    @Override
    public String toString(){
        return "TasksDomain{" +
                "mName='" + mName + '\'' +
                ", mId=" + mId +
                ", mStatus='" + mStatus + '\'' +
                ", mSummary='" + mSummary + '\'' +
                ", mPriorityId=" + mPriorityId +
                '}';
    }
}
