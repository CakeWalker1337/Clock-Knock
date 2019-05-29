package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.base.presentation.BaseView;

import java.util.Date;

/**
 * Presenter interface provides worklog methods to presenter object.
 *
 * @param <VIEW> View class
 */
public interface WorklogPresenter<VIEW extends BaseView> extends BasePresenter<VIEW>{

    /**
     * Loads tasks from API and throws it into presenter through ViewState.
     */
    void onDataRequest(long aTaskId, @NonNull String aTaskKey);

    /**
     * Method calls when timer stop button has clicked.
     *
     * @param aTaskId task key (Ex: MISC-303)
     * @param aDescription description of the worklog
     * @param aTimeSpentSeconds time displays on the timer in seconds.
     */
    void onWorklogAdd(long aTaskId, @NonNull String aDescription, int aTimeSpentSeconds);

    /**
     * Method calls when worklog item has been clicked.
     *
     * @param aTaskId task key (Ex: MISC-303)
     */
    void onWorklogEdit(int aPosition, WorklogAdapterItem aOldItem, long aTaskId, @NonNull String aDescription, int aTimeSpentSeconds);

    void onWorklogDelete(int aPosition, long aTaskId, @NonNull WorklogAdapterItem aWorklogItem);

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
    void onStartButtonClicked(long aTaskId, @NonNull String aTaskKey);

    /**
     * Calls when stop button is clicked
     */
    void onStopButtonClicked();

    /**
     * Calls when action is got from extra values
     *
     * @param aAction Action string value
     */
    void onActionGot(@NonNull String aAction);

    /**
     * Gets task id of current timer
     *
     * @return Taks id string
     */
    long getTimerTask();
}
