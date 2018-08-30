package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO class for data about author of worklog.
 */
public class WorklogAuthorEntity{

    @SerializedName("key")
    private String mKey;

    @Override
    public int hashCode(){

        return Objects.hash(mKey);
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        WorklogAuthorEntity that = (WorklogAuthorEntity) aObject;
        return Objects.equals(mKey, that.mKey);
    }

    @Override
    public String toString(){
        return "WorklogAuthorEntity{" +
                "mKey='" + mKey + '\'' +
                '}';
    }

    /**
     * Gets key of author. (username)
     *
     * @return key of author.
     */
    @NonNull
    public String getKey(){
        return mKey;
    }

    public void setKey(@NonNull String mKey){
        this.mKey = mKey;
    }

}
