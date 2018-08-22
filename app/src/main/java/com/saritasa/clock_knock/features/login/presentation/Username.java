package com.saritasa.clock_knock.features.login.presentation;

import com.saritasa.clock_knock.features.login.data.UsernameEntity;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import java.util.Objects;

public class Username{

    private String mUsername;

    public Username(final String aUsername){
        mUsername = aUsername;
    }

    public String getUsername(){
        return mUsername;
    }

    public void setUsername(String aUsername){
        mUsername = aUsername;
    }

    @Override
    public boolean equals(final Object aObject){
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
