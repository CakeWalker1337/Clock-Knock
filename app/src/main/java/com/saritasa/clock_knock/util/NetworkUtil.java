package com.saritasa.clock_knock.util;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.ApiError;
import com.saritasa.clock_knock.base.network.exception.RetrofitException;

import static com.saritasa.clock_knock.util.Constants.UNDEFINED_VALUE;

/**
 * A final class for some network operations (by Konstantin Veretelnikov)
 */
public final class NetworkUtil{

    private NetworkUtil(){
    }

    /**
     * Get http code from response exception
     *
     * @param aThrowable exception of response
     * @return http code
     */
    public static int getHttpCode(@NonNull Throwable aThrowable){
        if(aThrowable instanceof RetrofitException){
            RetrofitException retrofitException = (RetrofitException) aThrowable;
            return retrofitException.getHttpCode();
        }
        return UNDEFINED_VALUE;
    }

    /**
     * Get network error message
     *
     * @param aThrowable exception
     * @param aError default error message
     * @return error message
     */
    @NonNull
    public static String getNetworkErrorMessage(@NonNull Throwable aThrowable, @NonNull String aError){
        if(aThrowable instanceof RetrofitException){
            RetrofitException retrofitException = (RetrofitException) aThrowable;
            if(retrofitException.getKind() == RetrofitException.Kind.HTTP){
                ApiError apiError = retrofitException.convertTo(ApiError.class);
                return apiError != null ? apiError.getMessage() : aError;
            } else{
                return aError;
            }
        } else{
            return aError;
        }
    }
}