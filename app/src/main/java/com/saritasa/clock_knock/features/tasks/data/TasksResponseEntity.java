package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class TasksResponseEntity{

    @SerializedName("fields")
    private TasksFieldsEntity mFields;
    @SerializedName("key")
    private String mKey;
    @SerializedName("id")
    private String mId;

    @Override
    public int hashCode(){

        return Objects.hash(mFields, mKey, mId);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksResponseEntity that = (TasksResponseEntity) aO;
        return Objects.equals(mFields, that.mFields) &&
                Objects.equals(mKey, that.mKey) &&
                Objects.equals(mId, that.mId);
    }

    @Override
    public String toString(){
        return "TasksResponseEntity{" +
                "mFields=" + mFields +
                ", mKey='" + mKey + '\'' +
                ", mId='" + mId + '\'' +
                '}';
    }

    public TasksFieldsEntity getFields(){
        return mFields;
    }

    public void setFields(TasksFieldsEntity aFields){
        mFields = aFields;
    }

    public String getKey(){
        return mKey;
    }

    public void setKey(String aKey){
        mKey = aKey;
    }

    public String getId(){
        return mId;
    }

    public void setId(String aId){
        mId = aId;
    }
}
