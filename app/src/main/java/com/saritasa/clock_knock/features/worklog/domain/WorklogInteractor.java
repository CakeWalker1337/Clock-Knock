package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.util.Strings;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Interactor interface provides worklog methods to interactor object.
 */
public interface WorklogInteractor extends BaseInteractor{

    /**
     * Loads worklogs from API. Sorts and filtrates data.
     *
     * @param aTaskId task key for loading data (Ex: MISC-303).
     * @return observable with domain objects.
     */
    @NonNull
    ArrayList<WorklogDomain> loadWorklog(long aTaskId);

    /**
     * Creates new worklog on JIRA server through API.
     *
     * @param aTaskId task key for creating new worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     * @return maybe object contains domain object equals of created object on JIRA server.
     */
    long createWorklog(long aTaskId, @NonNull WorklogDomain aWorklogDomain);

    /**
     * Updates worklog on JIRA server through API.
     *
     * @param aTaskId task key for updating worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     */
    int updateWorklog(long aTaskId, @NonNull WorklogDomain aWorklogDomain);

    int deleteWorklog(long aTaskId, @NonNull WorklogDomain aWorklogDomain);

    /**
     * Checks timer activity
     *
     * @return true if timer is active, false otherwise
     */
    boolean isTimerActive();

    /**
     * Gets time string formatted by pattern {@value Strings#TIME_PATTERN}
     *
     * @param aTime time in milliseconds
     * @return Formatted time string
     */
    @NonNull
    String getFormattedTime(long aTime);

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
     * Saves task id and start timestamp to storage
     *
     * @param aTaskKey Taks id string
     * @return Start timestamp long value
     */
    long saveTimerData(long aTaskId, @NonNull String aTaskKey);

    /**
     *  Clears timer data in storage
     */
    void clearTimerData();

    /**
     * Gets task id of current timer
     *
     * @return Task id string
     */
    long getTimerTask();
}

