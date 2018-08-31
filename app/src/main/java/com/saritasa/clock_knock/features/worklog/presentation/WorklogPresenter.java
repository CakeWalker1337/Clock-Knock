package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.base.presentation.BaseView;

/**
 * Presenter interface provides worklog methods to presenter object.
 *
 * @param <VIEW> View class
 */
public interface WorklogPresenter<VIEW extends BaseView> extends BasePresenter<VIEW>{

    /**
     * Loads tasks from API and throws it into presenter through ViewState.
     */
    void onDataRequest(@NonNull String aTaskKey);

    /**
     * Method calls when timer stop button has clicked.
     *
     * @param aTaskKey task key (Ex: MISC-303)
     * @param aDescription description of the worklog
     * @param aTimeSpentSeconds time displays on the timer in seconds.
     */
    void onTimerStopped(@NonNull String aTaskKey, @NonNull String aDescription, int aTimeSpentSeconds);

    /**
     * Method calls when worklog item has been clicked.
     *
     * @param aTaskKey task key (Ex: MISC-303)
     * @param aWorklogAdapterItem worklog item which has been clicked.
     */
    void onWorklogClicked(@NonNull String aTaskKey, @NonNull WorklogAdapterItem aWorklogAdapterItem);

    /**
     * Checks timer activity
     *
     * @return true if timer is active, false otherwise
     */
    boolean isTimerActive();

    /**
     * Calls when timer ticked
     *
     * @param aTime current time on timer in seconds
     */
    void onTimerTicked(long aTime);

    /**
     * Gets hours of timer interval
     *
     * @return hours value
     */
    int getHours();

    /**
     * Gets minutes of timer interval
     *
     * @return minutes value
     */
    int getMinutes();

    /**
     * Calls when start button is clicked
     *
     * @param aTaskKey Task id string
     */
    void onStartButtonClicked(String aTaskKey);

    /**
     * Calls when stop button is clicked
     */
    void onStopButtonClicked();

    /**
     * Calls when action is got from extra values
     *
     * @param aAction Action string value
     */
    void onActionGot(String aAction);

    /**
     * Gets task id of current timer
     *
     * @return Taks id string
     */
    String getTimerTask();
}
