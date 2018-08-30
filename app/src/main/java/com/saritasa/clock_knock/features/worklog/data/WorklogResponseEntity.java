package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

/**
 * POJO class for serializing JSON object from API.
 */
public class WorklogResponseEntity{

    @SerializedName("worklogs")
    private ArrayList<WorklogInputEntity> mWorklogs;

    @Override
    public int hashCode(){

        return Objects.hash(mWorklogs);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        WorklogResponseEntity that = (WorklogResponseEntity) aO;
        return Objects.equals(mWorklogs, that.mWorklogs);
    }

    @Override
    public String toString(){
        return "WorklogResponseEntity{" +
                "mWorklogs=" + mWorklogs +
                '}';
    }

    /**
     * Gets list of worklog objects.
     *
     * @return arraylist of worklog objects
     */
    @NonNull
    public ArrayList<WorklogInputEntity> getWorklogs(){
        return mWorklogs;
    }

    /**
     * Gets list of worklog objects.
     *
     * @@param aWorklogs arraylist of worklog objects
     */
    public void setWorklogs(@NonNull final ArrayList<WorklogInputEntity> aWorklogs){
        mWorklogs = aWorklogs;
    }
}
