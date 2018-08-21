package com.saritasa.clock_knock.features.main.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class UsernameEntity{

    @SerializedName("key")
    private String mKey;

    public UsernameEntity(final String aKey){
        mKey = aKey;
    }

    public String getKey(){
        return mKey;
    }

    public void setKey(String aKey){
        mKey = aKey;
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        UsernameEntity that = (UsernameEntity) aObject;
        return Objects.equals(getKey(), that.getKey());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getKey());
    }
}
