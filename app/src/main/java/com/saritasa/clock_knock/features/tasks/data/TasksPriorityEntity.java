package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class TasksPriorityEntity{

    @SerializedName("iconUrl")
    private String mIconurl;
    @SerializedName("name")
    private String mName;
    @SerializedName("id")
    private String mPriorityId;

    @Override
    public int hashCode(){

        return Objects.hash(mIconurl, mName, mPriorityId);
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
                Objects.equals(mName, that.mName) &&
                Objects.equals(mPriorityId, that.mPriorityId);
    }

    @Override
    public String toString(){
        return "TasksPriorityEntity{" +
                "mIconurl='" + mIconurl + '\'' +
                ", mName='" + mName + '\'' +
                ", mPriorityId='" + mPriorityId + '\'' +
                '}';
    }

    public String getPriorityId(){
        return mPriorityId;
    }

    public void setPriorityId(final String aPriorityId){
        mPriorityId = aPriorityId;
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
