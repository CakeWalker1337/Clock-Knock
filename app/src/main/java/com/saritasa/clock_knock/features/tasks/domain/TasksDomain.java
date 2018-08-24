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
    private String mPriorityIconUrl;
    private String mProjectAvatarUrl;
    private String mStatus;
    private String mSummary;
    private int mPriorityId;

    @Override
    public int hashCode(){

        return Objects.hash(mName, mId, mPriorityIconUrl, mProjectAvatarUrl, mStatus, mSummary);
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        TasksDomain that = (TasksDomain) aObject;
        return Objects.equals(mName, that.mName) &&
                Objects.equals(mId, that.mId) &&
                Objects.equals(mPriorityIconUrl, that.mPriorityIconUrl) &&
                Objects.equals(mProjectAvatarUrl, that.mProjectAvatarUrl) &&
                Objects.equals(mStatus, that.mStatus) &&
                Objects.equals(mSummary, that.mSummary);
    }

    @Override
    public String toString(){
        return "TasksDomain{" +
                "mName='" + mName + '\'' +
                ", mId='" + mId + '\'' +
                ", mPriorityIconUrl='" + mPriorityIconUrl + '\'' +
                ", mProjectAvatarUrl='" + mProjectAvatarUrl + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mSummary='" + mSummary + '\'' +
                '}';
    }

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
    public String getPriorityIconUrl(){
        return mPriorityIconUrl;
    }

    public void setPriorityIconUrl(@NonNull final String aPriorityIconUrl){
        mPriorityIconUrl = aPriorityIconUrl;
    }

    @NonNull
    public String getProjectAvatarUrl(){
        return mProjectAvatarUrl;
    }

    public void setProjectAvatarUrl(@NonNull final String aProjectAvatarUrl){
        mProjectAvatarUrl = aProjectAvatarUrl;
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

    /**
     * Converts this object to TasksAdapterItem.
     *
     * @return Converted object.
     */

}
