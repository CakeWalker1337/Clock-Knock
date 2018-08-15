package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class TasksPriorityEntity{

    @SerializedName("iconUrl")
    private String mIconurl;
    @SerializedName("name")
    private String mName;

    @Override
    public int hashCode(){

        return Objects.hash(mIconurl, mName);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksPriorityEntity that = (TasksPriorityEntity) aO;
        return Objects.equals(mIconurl, that.mIconurl) &&
                Objects.equals(mName, that.mName);
    }

    @Override
    public String toString(){
        return "TasksPriorityEntity{" +
                "mIconurl='" + mIconurl + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }

    public String getIconurl(){
        return mIconurl;
    }

    public void setIconurl(String aIconurl){
        mIconurl = aIconurl;
    }

    public String getName(){
        return mName;
    }

    public void setName(String aName){
        mName = aName;
    }
}
