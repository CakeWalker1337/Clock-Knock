package com.saritasa.clock_knock.features.main.presentation;

import com.saritasa.clock_knock.base.presentation.BaseView;

/**
 * An interface for providing methods from presenter to view
 */
public interface MainView extends BaseView{

    /**
     * Starts timer service
     *
     * @param aTaskId Task id string
     * @param aTimestamp Timestamp value
     */
    void startTimer(String aTaskId, long aTimestamp);
}
