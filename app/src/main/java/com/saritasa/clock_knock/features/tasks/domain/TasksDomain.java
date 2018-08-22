package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.presentation.TasksAdapterItem;

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
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksDomain that = (TasksDomain) aO;
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

    public int getPriorityId(){
        return mPriorityId;
    }

    public void setPriorityId(final int aPriorityId){
        mPriorityId = aPriorityId;
    }

    public String getName(){
        return mName;
    }

    public void setName(final String aName){
        mName = aName;
    }

    public String getId(){
        return mId;
    }

    public void setId(final String aId){
        mId = aId;
    }

    public String getPriorityIconUrl(){
        return mPriorityIconUrl;
    }

    public void setPriorityIconUrl(final String aPriorityIconUrl){
        mPriorityIconUrl = aPriorityIconUrl;
    }

    public String getProjectAvatarUrl(){
        return mProjectAvatarUrl;
    }

    public void setProjectAvatarUrl(final String aProjectAvatarUrl){
        mProjectAvatarUrl = aProjectAvatarUrl;
    }

    public String getStatus(){
        return mStatus;
    }

    public void setStatus(final String aStatus){
        mStatus = aStatus;
    }

    public String getSummary(){
        return mSummary;
    }

    public void setSummary(final String aSummary){
        mSummary = aSummary;
    }

    /**
     * Converts this object to TasksAdapterItem.
     *
     * @return Converted object.
     */
    public TasksAdapterItem toTasksObject(){
        TasksAdapterItem tasksAdapterItem = new TasksAdapterItem();
        tasksAdapterItem.setId(getId());
        tasksAdapterItem.setName(getName());
        tasksAdapterItem.setPriorityIconUrl(getPriorityIconUrl());
        tasksAdapterItem.setProjectAvatarUrl(getProjectAvatarUrl());
        tasksAdapterItem.setStatus(getStatus());
        tasksAdapterItem.setSummary(getSummary());
        return tasksAdapterItem;
    }
}
