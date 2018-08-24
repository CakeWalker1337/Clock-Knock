package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksAvatarUrlsEntity{

    @SerializedName("32x32")
    private String mLargeAvatarUrl;

    /**
     * Constructs AvatarUrlEntity object with params.
     *
     * @param aLargeAvatarUrl - avatar url for downloading.
     */
    public TasksAvatarUrlsEntity(@Nullable final String aLargeAvatarUrl){
        mLargeAvatarUrl = aLargeAvatarUrl;
    }

    @Override
    public int hashCode(){
        return Objects.hash(mLargeAvatarUrl);
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        TasksAvatarUrlsEntity that = (TasksAvatarUrlsEntity) aObject;
        return Objects.equals(mLargeAvatarUrl, that.mLargeAvatarUrl);
    }

    @Override
    public String toString(){
        return "TasksAvatarUrlsEntity{" +
                "mLargeAvatarUrl='" + mLargeAvatarUrl + '\'' +
                '}';
    }

    @Nullable
    public String getLargeAvatarUrl(){
        return mLargeAvatarUrl;
    }

    public void setLargeAvatarUrl(@Nullable final String aLargeAvatarUrl){
        mLargeAvatarUrl = aLargeAvatarUrl;
    }
}
