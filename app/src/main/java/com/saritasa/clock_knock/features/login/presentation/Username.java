package com.saritasa.clock_knock.features.login.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.features.login.data.UsernameEntity;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import java.util.Objects;

/**
 * A data-class for holding the username when got it from interactor
 */
public class Username{

    private String mUsername;

    /**
     * @param aUsername Username string
     */
    public Username(@NonNull final String aUsername){
        mUsername = aUsername;
    }

    /**
     * Gets the username
     *
     * @return Username string
     */
    @NonNull
    public String getUsername(){
        return mUsername;
    }

    /**
     * Sets the username
     *
     * @param aUsername Username string
     */
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
        Username that = (Username) aObject;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUsername());
    }
}
