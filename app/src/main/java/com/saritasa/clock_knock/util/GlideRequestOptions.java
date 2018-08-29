package com.saritasa.clock_knock.util;

import android.support.annotation.NonNull;

import com.bumptech.glide.request.RequestOptions;
import com.saritasa.clock_knock.R;

/**
 * Configuration class for Glide. Contains request options.
 */
public final class GlideRequestOptions{

    private static RequestOptions sTasksRequestOptions;

    private GlideRequestOptions(){

    }


    /**
     * Singleton realization of configured request options for Glide.
     *
     * @return request options for glide.
     */
    @NonNull
    public static RequestOptions getTasksRequestOptions(){
        if(sTasksRequestOptions == null){
            sTasksRequestOptions = new RequestOptions();
            sTasksRequestOptions.placeholder(R.drawable.ic_error_outline_24dp);
        }
        return sTasksRequestOptions;
    }
}
