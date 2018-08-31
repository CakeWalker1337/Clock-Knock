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

}
