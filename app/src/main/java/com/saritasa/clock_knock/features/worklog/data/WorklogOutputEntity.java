package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO class for serializing data to JSON object.
 */
public class WorklogOutputEntity{

    private transient String mId;

    @SerializedName("comment")
    private String mComment;
    @SerializedName("timeSpentSeconds")
    private int mTimeSpentSeconds;
    @SerializedName("started")
    private String mUpdated;

    @Override
    public int hashCode(){

        return Objects.hash(mId, mComment, mTimeSpentSeconds, mUpdated);
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        WorklogOutputEntity that = (WorklogOutputEntity) aObject;
        return mTimeSpentSeconds == that.mTimeSpentSeconds &&
                Objects.equals(mId, that.mId) &&
                Objects.equals(mComment, that.mComment) &&
                Objects.equals(mUpdated, that.mUpdated);
    }

    @Override
    public String toString(){
        return "WorklogOutputEntity{" +
                "mId='" + mId + '\'' +
                ", mComment='" + mComment + '\'' +
                ", mTimeSpentSeconds=" + mTimeSpentSeconds +
                ", mUpdated='" + mUpdated + '\'' +
                '}';
    }

    /**
     * Gets updated date of worklog object.
     *
     * @return updated date.
     */
    @NonNull
    public String getUpdated(){
        return mUpdated;
    }

    /**
     * Sets updated date of worklog object.
     *
     * @param aUpdated updated date.
     */
    public void setUpdated(@NonNull final String aUpdated){
        mUpdated = aUpdated;
    }

    /**
     * Gets description of worklog object.
     *
     * @return description of worklog.
     */
    @NonNull
    public String getComment(){
        return mComment;
    }

    /**
     * Sets description of worklog object.
     *
     * @param aComment description of worklog.
     */
    public void setComment(@NonNull final String aComment){
        mComment = aComment;
    }

    /**
     * Gets time spent on work int the worklog object.
     *
     * @return time spent on work int the worklog object.
     */
    public int getTimeSpentSeconds(){
        return mTimeSpentSeconds;
    }

    /**
     * Sets time spent on work int the worklog object.
     *
     * @param aTimeSpentSeconds time spent on work int the worklog object.
     */
    public void setTimeSpentSeconds(final int aTimeSpentSeconds){
        mTimeSpentSeconds = aTimeSpentSeconds;
    }

    /**
     * Gets id of worklog object.
     *
     * @return id of worklog.
     */
    @NonNull
    public String getId(){
        return mId;
    }

    /**
     * Sets id of worklog object.
     *
     * @param aId id of worklog.
     */
    public void setId(@NonNull final String aId){
        mId = aId;
    }
}
