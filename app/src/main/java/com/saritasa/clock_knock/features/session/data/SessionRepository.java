package com.saritasa.clock_knock.features.session.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An interface to provide methods working with global data storages like BuildConfig, SharedPreferences, etc.
 */
public interface SessionRepository{

    /**
     * Clears all data in the storage
     */
    void clearAllData();

    /**
     * Saves the start timestamp
     *
     * @param aTimestamp Start timestamp number
     */
    void saveStartTimestamp(long aTimestamp);

    /**
     * Gets the start timestamp
     *
     * @return Start timestamp number
     */
    long getStartTimestamp();

    /**
     * Saves the task id
     *
     * @param aTaskId Task id string
     */
    void saveTaskId(@NonNull String aTaskId);

    /**
     * Gets the task id
     *
     * @return Task id string
     */
    @Nullable
    String getTaskId();

    /**
     * Clears timer data from storage
     */
    void clearTimerData();
}
