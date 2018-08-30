package com.saritasa.moxytest.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Class contains singleton instance of date formatter for parsing date from JIRA output.
 */
public class DateFormatter{

    private static volatile SimpleDateFormat INSTANCE = null;

    /**
     * Gets instance of date formatter;
     *
     * @return instance of date formatter.
     */
    public static SimpleDateFormat getInstance(){
        SimpleDateFormat localInstance = INSTANCE;
        if(localInstance == null){
            synchronized(SimpleDateFormat.class){
                localInstance = INSTANCE;
                if(localInstance == null){
                    INSTANCE = localInstance = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
                }
            }
        }
        return localInstance;
    }

}
