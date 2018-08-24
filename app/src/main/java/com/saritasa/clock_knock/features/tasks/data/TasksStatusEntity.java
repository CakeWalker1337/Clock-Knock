package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksStatusEntity{

    /**
     * Constructs TasksStatusEntity object with params.
     *
     * @param aName - name of status
     */
    public TasksStatusEntity(@NonNull String aName){
        mName = aName;
    }

    @SerializedName("name")
    private String mName;

    @Override
    public int hashCode(){

        return Objects.hash(mName);
    }

    @Override
    public boolean equals(@Nullable final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksStatusEntity that = (TasksStatusEntity) aO;
        return Objects.equals(mName, that.mName);
    }

    @Override
    public String toString(){
        return "TasksStatusEntity{" +
                "mName='" + mName + '\'' +
                '}';
    }

    @NonNull
    public String getName(){
        return mName;
    }

    public void setName(@NonNull String aName){
        mName = aName;
    }
}
