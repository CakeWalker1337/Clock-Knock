package com.saritasa.clock_knock.utils;

import com.bumptech.glide.request.RequestOptions;
import com.saritasa.clock_knock.R;

/**
 * Configuration class for Glide. Contains request options.
 */
public class GlideRequestOptions{

    public static RequestOptions sTasksRequestOptions;

    /**
     * Singleton realization of configured request options for Glide.
     *
     * @return request options for glide.
     */
    public static RequestOptions getTasksRequestOptions(){
        if(sTasksRequestOptions == null){
            sTasksRequestOptions = new RequestOptions();
            sTasksRequestOptions.placeholder(R.drawable.ic_error_outline_24dp);
        }
        return sTasksRequestOptions;
    }
}
