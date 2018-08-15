package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class TasksProjectEntity{

    @SerializedName("avatarUrls")
    private TasksAvatarUrlsEntity mAvatarurls;
    @SerializedName("name")
    private String mName;

    @Override
    public int hashCode(){

        return Objects.hash(mAvatarurls, mName);
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
        return Objects.equals(mAvatarurls, that.mAvatarurls) &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public String toString(){
        return "TasksProjectEntity{" +
                "mAvatarurls=" + mAvatarurls +
                ", mName='" + mName + '\'' +
                '}';
    }

    public TasksAvatarUrlsEntity getAvatarurls(){
        return mAvatarurls;
    }

    public void setAvatarurls(TasksAvatarUrlsEntity aAvatarurls){
        mAvatarurls = aAvatarurls;
    }

    public String getName(){
        return mName;
    }

    public void setName(String aName){
        mName = aName;
    }
}
