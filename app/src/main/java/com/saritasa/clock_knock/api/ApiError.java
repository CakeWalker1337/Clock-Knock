package com.saritasa.clock_knock.api;

import android.support.annotation.NonNull;

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
    public ApiError(@NonNull final Integer aStatus, final String aMessage){
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

    @NonNull
    public Integer getStatus(){
        return mStatus;
    }

    public void setStatus(@NonNull final Integer aStatus){
        mStatus = aStatus;
    }

    @NonNull
    public String getMessage(){
        return mMessage;
    }

    public void setMessage(@NonNull final String aMessage){
        mMessage = aMessage;
    }
}
