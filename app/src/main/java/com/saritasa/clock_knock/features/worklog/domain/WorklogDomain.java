package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

/**
 * Data class for domain layer of project.
 */
public class WorklogDomain{

    private long mId;
    private String mDescription;
    private Date mCreationDate;
    private int mTimeSpentSeconds;

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(!(aObject instanceof WorklogDomain)){
            return false;
        }
        WorklogDomain that = (WorklogDomain) aObject;
        return getId() == that.getId() &&
                getTimeSpentSeconds() == that.getTimeSpentSeconds() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getCreationDate(), that.getCreationDate());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getDescription(), getCreationDate(), getTimeSpentSeconds());
    }

    @Override
    public String toString(){
        return "WorklogDomain{" +
                "mId=" + mId +
                ", mDescription='" + mDescription + '\'' +
                ", mCreationDate=" + mCreationDate +
                ", mTimeSpentSeconds=" + mTimeSpentSeconds +
                '}';
    }

    /**
     * Gets id of worklog object.
     *
     * @return id of worklog.
     */
    public long getId(){
        return mId;
    }

    /**
     * Gets id of worklog object.
     *
     * @param aId id of worklog.
     */
    public void setId(final long aId){
        mId = aId;
    }

    /**
     * Gets description (comment) of worklog object.
     *
     * @return description (comment) of worklog.
     */
    @NonNull
    public String getDescription(){
        return mDescription;
    }

    /**
     * Gets description (comment) of worklog object.
     *
     * @param aComment description (comment) of worklog.
     */
    public void setDescription(@NonNull final String aComment){
        mDescription = aComment;
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
}
