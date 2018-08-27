package com.saritasa.clock_knock.features.login.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * A data-class for holding the username when got it from repository
 */
public class UsernameDomain{

    private String mUsername;

    /**
     * @param aUsername Username string
     */
    public UsernameDomain(@NonNull final String aUsername){
        mUsername = aUsername;
    }

    @NonNull
    public String getUsername(){
        return mUsername;
    }

    public void setUsername(@NonNull String aUsername){
        mUsername = aUsername;
    }

    @Override
    public boolean equals(@Nullable final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        UsernameDomain that = (UsernameDomain) aObject;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUsername());
    }
}
