package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksProjectEntity{

    @SerializedName("avatarUrls")
    private TasksAvatarUrlsEntity mAvatarUrls;

    /**
     * @param aAvatarUrls - project avatar url
     */
    public TasksProjectEntity(@NonNull TasksAvatarUrlsEntity aAvatarUrls){
        mAvatarUrls = aAvatarUrls;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mAvatarUrls);
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        TasksProjectEntity that = (TasksProjectEntity) aObject;
        return Objects.equals(mAvatarUrls, that.mAvatarUrls);
    }

    @Override
    public String toString(){
        return "TasksProjectEntity{" +
                "mAvatarUrls=" + mAvatarUrls +
                '}';
    }

    /**
     * Gets project avatar URL entity.
     *
     * @return project avatar URL entity
     */
    @NonNull
    public TasksAvatarUrlsEntity getAvatarUrls(){
        return mAvatarUrls;
    }

    /**
     * Sets project avatar URL entity.
     * @param aAvatarUrls project avatar URL entity.
     */
    public void setAvatarUrls(@NonNull TasksAvatarUrlsEntity aAvatarUrls){
        mAvatarUrls = aAvatarUrls;
    }

}
