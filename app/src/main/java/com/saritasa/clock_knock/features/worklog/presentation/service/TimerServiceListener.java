package com.saritasa.clock_knock.features.worklog.presentation.service;

/**
 * An interface for providing methods from timer service to any binded fragment
 */
public interface TimerServiceListener{

    /**
     * Calls when the timer is ticked one time
     *
     * @param aTime Formatted current time string
     */
    void timerTicked(long aTime);
}
