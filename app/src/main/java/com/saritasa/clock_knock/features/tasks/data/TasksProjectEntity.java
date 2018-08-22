package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksProjectEntity{

    @SerializedName("avatarUrls")
    private TasksAvatarUrlsEntity mAvatarUrls;
    @SerializedName("name")
    private String mName;

    public TasksProjectEntity(String aName, TasksAvatarUrlsEntity aAvatarUrls){
        mName = aName;
        mAvatarUrls = aAvatarUrls;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mAvatarUrls, mName);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksProjectEntity that = (TasksProjectEntity) aO;
        return Objects.equals(mAvatarUrls, that.mAvatarUrls) &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public String toString(){
        return "TasksProjectEntity{" +
                "mAvatarUrls=" + mAvatarUrls +
                ", mName='" + mName + '\'' +
                '}';
    }

    public TasksAvatarUrlsEntity getAvatarUrls(){
        return mAvatarUrls;
    }

    public void setAvatarUrls(TasksAvatarUrlsEntity aAvatarUrls){
        mAvatarUrls = aAvatarUrls;
    }

    public String getName(){
        return mName;
    }

    public void setName(String aName){
        mName = aName;
    }
}
