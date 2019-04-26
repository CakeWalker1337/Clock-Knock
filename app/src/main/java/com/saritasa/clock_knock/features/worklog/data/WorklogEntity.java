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

    @ColumnInfo(name = "time_spent")
    private String mTimeSpent;

    @ColumnInfo(name = "task_id")
    private String taskId;

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

    public String getTimeSpent(){
        return mTimeSpent;
    }

    public void setTimeSpent(final String aTimeSpent){
        mTimeSpent = aTimeSpent;
    }

    public String getTaskId(){
        return taskId;
    }

    public void setTaskId(final String aTaskId){
        taskId = aTaskId;
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
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTimeSpent(), that.getTimeSpent()) &&
                Objects.equals(getTaskId(), that.getTaskId());
    }

    @Override
    public int hashCode(){

        return Objects.hash(getId(), getDescription(), getTimeSpent(), getTaskId());
    }

    @Override
    public String toString(){
        return "WorklogEntity{" +
                "mId=" + mId +
                ", mDescription='" + mDescription + '\'' +
                ", mTimeSpent='" + mTimeSpent + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
