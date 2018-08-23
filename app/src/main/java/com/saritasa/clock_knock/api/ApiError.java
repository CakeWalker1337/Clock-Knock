package com.saritasa.clock_knock.api;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Data class for holding the API error message and code
 */
public class ApiError{

    @SerializedName("status")
    private Integer mStatus;

    @SerializedName("message")
    private String mMessage;

    /**
     * @param aStatus A status error code of request
     * @param aMessage An error message
     */
    public ApiError(final Integer aStatus, final String aMessage){
        mStatus = aStatus;
        mMessage = aMessage;
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        ApiError apiError = (ApiError) aObject;
        return Objects.equals(getStatus(), apiError.getStatus()) &&
                Objects.equals(getMessage(), apiError.getMessage());
    }

    @Override
    public int hashCode(){

        return Objects.hash(getStatus(), getMessage());
    }

    public Integer getStatus(){
        return mStatus;
    }

    public void setStatus(final Integer aStatus){
        mStatus = aStatus;
    }

    public String getMessage(){
        return mMessage;
    }

    public void setMessage(final String aMessage){
        mMessage = aMessage;
    }
}
