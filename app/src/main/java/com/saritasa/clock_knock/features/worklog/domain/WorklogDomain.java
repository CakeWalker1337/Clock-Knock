package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

/**
 * Data class for domain layer of project.
 */
public class WorklogDomain{

    private String mId;
    private String mComment;
    private Date mCreationDate;
    private String mTimeSpent;
    private int mTimeSpentSeconds;
    private String mAuthorsKey;

    @Override
    public int hashCode(){
        return Objects.hash(mId, mComment, mCreationDate, mTimeSpent, mTimeSpentSeconds, mAuthorsKey);
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        WorklogDomain that = (WorklogDomain) aObject;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(mComment, that.mComment) &&
                Objects.equals(mCreationDate, that.mCreationDate) &&
                Objects.equals(mTimeSpent, that.mTimeSpent) &&
                Objects.equals(mTimeSpentSeconds, that.mTimeSpentSeconds) &&
                Objects.equals(mAuthorsKey, that.mAuthorsKey);
    }

    @Override
    public String toString(){
        return "WorklogDomain{" +
                "mId='" + mId + '\'' +
                ", mComment='" + mComment + '\'' +
                ", mCreationDate='" + mCreationDate + '\'' +
                ", mTimeSpent='" + mTimeSpent + '\'' +
                ", mTimeSpentSeconds='" + mTimeSpentSeconds + '\'' +
                ", mAuthorsKey='" + mAuthorsKey + '\'' +
                '}';
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
     * Gets id of worklog object.
     *
     * @param aId id of worklog.
     */
    public void setId(@NonNull final String aId){
        mId = aId;
    }

    /**
     * Gets description (comment) of worklog object.
     *
     * @return description (comment) of worklog.
     */
    @NonNull
    public String getComment(){
        return mComment;
    }

    /**
     * Gets description (comment) of worklog object.
     *
     * @param aComment description (comment) of worklog.
     */
    public void setComment(@NonNull final String aComment){
        mComment = aComment;
    }

    /**
     * Gets creation date of worklog object.
     *
     * @return creation date of worklog.
     */
    @NonNull
    public Date getCreationDate(){
        return mCreationDate;
    }

    /**
     * Gets creation date of worklog object.
     *
     * @param aCreationDate creation date of worklog.
     */
    public void setCreationDate(@NonNull final Date aCreationDate){
        mCreationDate = aCreationDate;
    }

    /**
     * Gets time spent on work in the worklog object.
     *
     * @return time spent on work in the worklog object.
     */
    @NonNull
    public String getTimeSpent(){
        return mTimeSpent;
    }

    /**
     * Gets time spent on work in the worklog object.
     *
     * @param aTimeSpent time spent on work in the worklog object.
     */
    public void setTimeSpent(@NonNull final String aTimeSpent){
        mTimeSpent = aTimeSpent;
    }

    /**
     * Gets time spent on work in the worklog object in seconds.
     *
     * @return time spent on work in the worklog object in seconds.
     */
    public int getTimeSpentSeconds(){
        return mTimeSpentSeconds;
    }

    /**
     * Sets time spent on work in the worklog object in seconds.
     *
     * @param aTimeSpentSeconds time spent on work in the worklog object in seconds.
     */
    public void setTimeSpentSeconds(final int aTimeSpentSeconds){
        mTimeSpentSeconds = aTimeSpentSeconds;
    }

    /**
     * Gets author's key of worklog object.
     *
     * @return author's key.
     */
    @NonNull
    public String getAuthorsKey(){
        return mAuthorsKey;
    }

    /**
     * Sets author's key of worklog object.
     *
     * @param aAuthor author's key of worklog object.
     */
    public void setAuthorsKey(@NonNull final String aAuthor){
        mAuthorsKey = aAuthor;
    }
}
