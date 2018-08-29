package com.saritasa.clock_knock.features.login.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    public UsernameEntity(@NonNull final String aKey){
        mKey = aKey;
    }

    /**
     * Gets the key field
     *
     * @return Key field string
     */
    @NonNull
    public String getKey(){
        return mKey;
    }

    /**
     * Sets the key field
     *
     * @param aKey Key field string
     */
    public void setKey(@NonNull String aKey){
        mKey = aKey;
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
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
