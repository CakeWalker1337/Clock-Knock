package com.saritasa.clock_knock.features.worklog.presentation.service;

import android.os.Binder;

/**
 * Binder class for timer service
 */
public abstract class TimerServiceBinder extends Binder{

    /**
     * Sets the timer service listener
     *
     * @param aListener Timer service listener
     */
    public abstract void setTimerListener(TimerServiceListener aListener);
}
