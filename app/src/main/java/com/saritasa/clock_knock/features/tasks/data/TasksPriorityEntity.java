package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksPriorityEntity{

    @SerializedName("iconUrl")
    private String mIconUrl;
    @SerializedName("name")
    private String mName;
    @SerializedName("id")
    private String mPriorityId;

    public TasksPriorityEntity(String aName, String aIconUrl, String aPriorityId){
        mName = aName;
        mPriorityId = aPriorityId;
        mIconUrl = aIconUrl;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mIconUrl, mName, mPriorityId);
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
        return Objects.equals(mIconUrl, that.mIconUrl) &&
                Objects.equals(mName, that.mName) &&
                Objects.equals(mPriorityId, that.mPriorityId);
    }

    @Override
    public String toString(){
        return "TasksPriorityEntity{" +
                "mIconUrl='" + mIconUrl + '\'' +
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

    public String getIconUrl(){
        return mIconUrl;
    }

    public void setIconUrl(String aIconUrl){
        mIconUrl = aIconUrl;
    }

    public String getName(){
        return mName;
    }

    public void setName(String aName){
        mName = aName;
    }
}
