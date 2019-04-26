package com.saritasa.clock_knock.features.tasks.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;

import com.saritasa.clock_knock.util.Constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;




@Entity(tableName = "tasks")
public class TaskEntity{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private String mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "priority")
    private int mPriority;

    @ColumnInfo(name = "status")
    private String mStatus;

    public String getId(){
        return mId;
    }

    public void setId(final String aId){
        mId = aId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(final String aTitle){
        mTitle = aTitle;
    }

    public String getDescription(){
        return mDescription;
    }

    public void setDescription(final String aDescription){
        mDescription = aDescription;
    }

    public int getPriority(){
        return mPriority;
    }

    public void setPriority(final int aPriority){
        mPriority = aPriority;
    }

    public String getStatus(){
        return mStatus;
    }

    public void setStatus(final String aStatus){
        mStatus = aStatus;
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(!(aObject instanceof TaskEntity)){
            return false;
        }
        TaskEntity that = (TaskEntity) aObject;
        return getPriority() == that.getPriority() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getTitle(), getDescription(), getPriority(), getStatus());
    }

    @Override
    public String toString(){
        return "TaskEntity{" +
                "mId='" + mId + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mPriority=" + mPriority +
                ", mStatus='" + mStatus + '\'' +
                '}';
    }
}
