package com.saritasa.clock_knock.features.worklog.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.saritasa.clock_knock.features.tasks.data.TaskEntity;

import java.util.Objects;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "worklogs",
        foreignKeys = @ForeignKey(
                entity = TaskEntity.class,
                parentColumns = "id",
                childColumns = "task_id",
                onDelete = CASCADE))
public class WorklogEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "time_spent_seconds")
    private long mTimeSpentSeconds;

    @ColumnInfo(name = "task_id")
    private long mTaskId;

    @ColumnInfo(name = "creation_date")
    private String mCreationDate;

    public long getId(){
        return mId;
    }

    public void setId(final long aId){
        mId = aId;
    }

    public String getDescription(){
        return mDescription;
    }

    public void setDescription(final String aDescription){
        mDescription = aDescription;
    }

    public long getTaskId(){
        return mTaskId;
    }

    public void setTaskId(final long aTaskId){
        mTaskId = aTaskId;
    }

    public String getCreationDate(){
        return mCreationDate;
    }

    public void setCreationDate(final String aCreationDate){
        mCreationDate = aCreationDate;
    }

    public long getTimeSpentSeconds(){
        return mTimeSpentSeconds;
    }

    public void setTimeSpentSeconds(final long aTimeSpentSeconds){
        mTimeSpentSeconds = aTimeSpentSeconds;
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(!(aObject instanceof WorklogEntity)){
            return false;
        }
        WorklogEntity that = (WorklogEntity) aObject;
        return getId() == that.getId() &&
                getTimeSpentSeconds() == that.getTimeSpentSeconds() &&
                getTaskId() == that.getTaskId() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getCreationDate(), that.getCreationDate());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getDescription(), getTimeSpentSeconds(), getTaskId(), getCreationDate());
    }

    @Override
    public String toString(){
        return "WorklogEntity{" +
                "mId=" + mId +
                ", mDescription='" + mDescription + '\'' +
                ", mTimeSpentSeconds=" + mTimeSpentSeconds +
                ", mTaskId=" + mTaskId +
                ", mCreationDate='" + mCreationDate + '\'' +
                '}';
    }
}
