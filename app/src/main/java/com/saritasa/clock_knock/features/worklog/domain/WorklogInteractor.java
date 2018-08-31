package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.util.Strings;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Interactor interface provides worklog methods to interactor object.
 */
public interface WorklogInteractor extends BaseInteractor{

    /**
     * Loads worklogs from API. Sorts and filtrates data.
     *
     * @param aTaskKey task key for loading data (Ex: MISC-303).
     * @return observable with domain objects.
     */
    @NonNull
    Observable<WorklogDomain> loadWorklog(@NonNull String aTaskKey);

    /**
     * Creates new worklog on JIRA server through API.
     *
     * @param aTaskKey task key for creating new worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     * @return maybe object contains domain object equals of created object on JIRA server.
     */
    @NonNull
    Maybe<WorklogDomain> createWorklog(@NonNull String aTaskKey, @NonNull WorklogDomain aWorklogDomain);

    /**
     * Updates worklog on JIRA server through API.
     *
     * @param aTaskKey task key for updating worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     */
    void saveWorklog(@NonNull String aTaskKey, @NonNull WorklogDomain aWorklogDomain);

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
    long saveTimerData(@NonNull String aTaskKey);

    /**
     *  Clears timer data in storage
     */
    void clearTimerData();

    /**
     * Gets task id of current timer
     *
     * @return Task id string
     */
    @Nullable
    String getTimerTask();
}

