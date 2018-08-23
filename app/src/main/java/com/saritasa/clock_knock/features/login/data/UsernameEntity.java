package com.saritasa.clock_knock.features.login.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * A data-class for holding the username when got it from API
 */
public class UsernameEntity{

    @SerializedName("key")
    private String mKey;

    /**
     * @param aKey Username string
     */
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
