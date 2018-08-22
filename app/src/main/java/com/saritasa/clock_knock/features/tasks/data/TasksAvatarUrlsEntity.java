package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * POJO Class for parsing JSON.
 */
public class TasksAvatarUrlsEntity{

    @SerializedName("16x16")
    private String mSmallAvatarUrl;

    @SerializedName("24x24")
    private String mMediumAvatarUrl;

    @SerializedName("32x32")
    private String mLargeAvatarUrl;

    @SerializedName("48x48")
    private String mXLargeAvatarUrl;

    public TasksAvatarUrlsEntity(final String aLargeAvatarUrl){
        mLargeAvatarUrl = aLargeAvatarUrl;
    }

    @Override
    public int hashCode(){

        return Objects.hash(mSmallAvatarUrl, mMediumAvatarUrl, mLargeAvatarUrl, mXLargeAvatarUrl);
    }

    @Override
    public boolean equals(final Object aO){
        if(this == aO){
            return true;
        }
        if(aO == null || getClass() != aO.getClass()){
            return false;
        }
        TasksAvatarUrlsEntity that = (TasksAvatarUrlsEntity) aO;
        return Objects.equals(mSmallAvatarUrl, that.mSmallAvatarUrl) &&
                Objects.equals(mMediumAvatarUrl, that.mMediumAvatarUrl) &&
                Objects.equals(mLargeAvatarUrl, that.mLargeAvatarUrl) &&
                Objects.equals(mXLargeAvatarUrl, that.mXLargeAvatarUrl);
    }

    @Override
    public String toString(){
        return "TasksAvatarUrlsEntity{" +
                "mSmallAvatarUrl='" + mSmallAvatarUrl + '\'' +
                ", mMediumAvatarUrl='" + mMediumAvatarUrl + '\'' +
                ", mLargeAvatarUrl='" + mLargeAvatarUrl + '\'' +
                ", mXLargeAvatarUrl='" + mXLargeAvatarUrl + '\'' +
                '}';
    }

    public String getSmallAvatarUrl(){
        return mSmallAvatarUrl;
    }

    public void setSmallAvatarUrl(final String aSmallAvatarUrl){
        mSmallAvatarUrl = aSmallAvatarUrl;
    }

    public String getMediumAvatarUrl(){
        return mMediumAvatarUrl;
    }

    public void setMediumAvatarUrl(final String aMediumAvatarUrl){
        mMediumAvatarUrl = aMediumAvatarUrl;
    }

    public String getXLargeAvatarUrl(){
        return mXLargeAvatarUrl;
    }

    public void setXLargeAvatarUrl(final String aXLargeAvatarUrl){
        mXLargeAvatarUrl = aXLargeAvatarUrl;
    }

    public String getLargeAvatarUrl(){
        return mLargeAvatarUrl;
    }

    public void setLargeAvatarUrl(final String aLargeAvatarUrl){
        mLargeAvatarUrl = aLargeAvatarUrl;
    }
}
