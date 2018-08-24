package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksIssueEntity{

    @SerializedName("fields")
    private TasksFieldsEntity mFields;
    @SerializedName("key")
    private String mName;
    @SerializedName("id")
    private String mId;

    /**
     * Constructs TasksIssueEntity object with params.
     *
     * @param aFields - Entity fields object
     * @param aName - name of issue
     * @param aId - id of issue
     */
    public TasksIssueEntity(@NonNull TasksFieldsEntity aFields, @NonNull String aName, @NonNull String aId){
        mId = aId;
        mName = aName;
        mFields = aFields;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mFields, mName, mId);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksIssueEntity that = (TasksIssueEntity) aO;
        return Objects.equals(mFields, that.mFields) &&
                Objects.equals(mName, that.mName) &&
                Objects.equals(mId, that.mId);
    }

    @Override
    public String toString(){
        return "TasksResponseEntity{" +
                "mFields=" + mFields +
                ", mName='" + mName + '\'' +
                ", mId='" + mId + '\'' +
                '}';
    }

    @NonNull
    public TasksFieldsEntity getFields(){
        return mFields;
    }

    public void setFields(@NonNull TasksFieldsEntity aFields){
        mFields = aFields;
    }

    @NonNull
    public String getName(){
        return mName;
    }

    public void setName(@NonNull String aName){
        mName = aName;
    }

    @NonNull
    public String getId(){
        return mId;
    }

    public void setId(@NonNull String aId){
        mId = aId;
    }



}
