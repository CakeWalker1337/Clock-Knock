package com.saritasa.clock_knock.util;

import android.widget.AdapterView;

/**
 * A final class for numeric constants
 */
public final class Constants{
    /**
     * Undefined value
     */
    public static final int UNDEFINED_VALUE = -1;

    /**
     * Ohe hour in milliseconds value
     */
    public static final int ONE_HOUR_MILLIS = 3600000;

    /**
     * Ohe minute in milliseconds value
     */
    public static final int ONE_MINUTE_MILLIS = 60000;

    /**
     * Ohe hour in milliseconds value
     */
    public static final int ONE_HOUR_SEC = 3600;

    /**
     * Ohe minute in milliseconds value
     */
    public static final int ONE_MINUTE_SEC = 60;


    public static final int PRIORITY_HIGH = 2;

    public static final int PRIORITY_MEDIUM = 1;

    public static final int PRIORITY_LOW = 0;

    public static final int PRIORITY_INVALID = AdapterView.INVALID_POSITION;

    private Constants(){
    }
}
