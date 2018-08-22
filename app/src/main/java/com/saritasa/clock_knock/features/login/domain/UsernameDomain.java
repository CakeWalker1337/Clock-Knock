package com.saritasa.clock_knock.features.login.domain;

import java.util.Objects;

public class UsernameDomain{

    private String mUsername;

    public UsernameDomain(final String aUsername){
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
        UsernameDomain that = (UsernameDomain) aObject;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getUsername());
    }
}
