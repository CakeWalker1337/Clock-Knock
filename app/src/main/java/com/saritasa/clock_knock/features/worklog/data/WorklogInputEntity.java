package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class WorklogInputEntity{

    @SerializedName("id")
    private String mId;
    @SerializedName("comment")
    private String mComment;
    @SerializedName("created")
    private String mCreationDate;
    @SerializedName("timeSpent")
    private String mTimeSpent;
    @SerializedName("timeSpentSeconds")
    private String mTimeSpentSeconds;
    @SerializedName("author")
    private WorklogAuthorEntity mAuthor;

    @Override
    public int hashCode(){
        return Objects.hash(mId, mComment, mCreationDate, mTimeSpent, mTimeSpentSeconds, mAuthor);
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        WorklogInputEntity that = (WorklogInputEntity) aObject;
        return Objects.equals(mId, that.mId) &&
                Objects.equals(mComment, that.mComment) &&
                Objects.equals(mCreationDate, that.mCreationDate) &&
                Objects.equals(mTimeSpent, that.mTimeSpent) &&
                Objects.equals(mTimeSpentSeconds, that.mTimeSpentSeconds) &&
                Objects.equals(mAuthor, that.mAuthor);
    }

    @Override
    public String toString(){
        return "WorklogInputEntity{" +
                "mId='" + mId + '\'' +
                ", mComment='" + mComment + '\'' +
                ", mCreationDate='" + mCreationDate + '\'' +
                ", mTimeSpent='" + mTimeSpent + '\'' +
                ", mTimeSpentSeconds='" + mTimeSpentSeconds + '\'' +
                ", mAuthor=" + mAuthor +
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
    public String getCreationDate(){
        return mCreationDate;
    }

    /**
     * Gets creation date of worklog object.
     *
     * @param aCreationDate creation date of worklog.
     */
    public void setCreationDate(@NonNull final String aCreationDate){
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
    @NonNull
    public String getTimeSpentSeconds(){
        return mTimeSpentSeconds;
    }

    /**
     * Sets time spent on work in the worklog object in seconds.
     *
     * @param aTimeSpentSeconds time spent on work in the worklog object in seconds.
     */
    public void setTimeSpentSeconds(@NonNull final String aTimeSpentSeconds){
        mTimeSpentSeconds = aTimeSpentSeconds;
    }

    /**
     * Gets author object contains information about author of worklog object.
     *
     * @return author object.
     */
    @NonNull
    public WorklogAuthorEntity getAuthor(){
        return mAuthor;
    }

    /**
     * Sets author object contains information about author of worklog object.
     *
     * @param aAuthor author object contains information about author of worklog object.
     */
    public void setAuthor(@NonNull final WorklogAuthorEntity aAuthor){
        mAuthor = aAuthor;
    }
}
