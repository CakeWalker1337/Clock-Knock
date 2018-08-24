package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksPriorityEntity{

    @SerializedName("iconUrl")
    private String mIconUrl;
    @SerializedName("id")
    private String mPriorityId;

    /**
     * Constructs TasksPriorityEntity object with params.
     *
     * @param aIconUrl - priority icon url
     * @param aPriorityId - priority id
     */
    public TasksPriorityEntity(@NonNull String aIconUrl, @NonNull String aPriorityId){
        mPriorityId = aPriorityId;
        mIconUrl = aIconUrl;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mIconUrl, mPriorityId);
    }

    @Override
    public boolean equals(@Nullable final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksPriorityEntity that = (TasksPriorityEntity) aO;
        return Objects.equals(mIconUrl, that.mIconUrl) &&
                Objects.equals(mPriorityId, that.mPriorityId);
    }

    @Override
    public String toString(){
        return "TasksPriorityEntity{" +
                "mIconUrl='" + mIconUrl + '\'' +
                ", mPriorityId='" + mPriorityId + '\'' +
                '}';
    }

    @NonNull
    public String getPriorityId(){
        return mPriorityId;
    }

    public void setPriorityId(@NonNull final String aPriorityId){
        mPriorityId = aPriorityId;
    }

    @NonNull
    public String getIconUrl(){
        return mIconUrl;
    }

    public void setIconUrl(@NonNull String aIconUrl){
        mIconUrl = aIconUrl;
    }

}
