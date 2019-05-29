package com.saritasa.clock_knock.util;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Class contains singleton instance of date formatter for parsing date from JIRA output.
 */
public class DateTimeFormatter{

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

    /**
     * Converts time in milliseconds to time format
     *
     * @param aTimeMillis Time in milliseconds
     * @param aTimePatternString Pattern string to format
     * @return Formatted time string
     */
    public static String longTimeMillisToFormat(long aTimeMillis, String aTimePatternString){
        return DurationFormatUtils.formatDuration(aTimeMillis, aTimePatternString);
    }

    /**
     * Converts time in seconds to format "HHh SSs" (Ex: "5h 10m")
     *
     * @param aTimeSeconds Time in seconds
     * @return Time in text format
     */
    public static String longTimeSecondsToText(long aTimeSeconds){
        long hours = aTimeSeconds / 3600;
        long minutes = (aTimeSeconds % 3600) / 60;

        StringBuilder sb = new StringBuilder();
        if(hours > 0){
            sb.append(hours).append("h ");
        }
        sb.append(minutes).append("m");

        return sb.toString();
    }

}
