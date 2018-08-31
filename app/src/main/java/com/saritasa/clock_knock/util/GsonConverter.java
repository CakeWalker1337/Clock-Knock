package com.saritasa.clock_knock.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter{

    private static volatile Gson INSTANCE = null;

    /**
     * Gets instance of date formatter;
     *
     * @return instance of date formatter.
     */
    public static Gson getInstance(){
        Gson localInstance = INSTANCE;
        if(localInstance == null){
            synchronized(Gson.class){
                localInstance = INSTANCE;
                if(localInstance == null){
                    GsonBuilder builder = new GsonBuilder();
                    INSTANCE = localInstance = builder.create();
                }
            }
        }
        return localInstance;
    }

}
