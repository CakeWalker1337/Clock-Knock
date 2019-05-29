package com.saritasa.clock_knock.base.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An interface for providing the operations with SharedPreferences class
 */
public interface PreferenceManager{

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
    void saveTaskId(long aTaskId);

    /**
     * Saves the task id
     *
     * @param aTaskKey Task id string
     */
    void saveTaskKey(@NonNull String aTaskKey);

    /**
     * Gets the task id
     *
     * @return Task id string
     */
    long getTaskId();

    /**
     * Gets the task id
     *
     * @return Task id string
     */
    @Nullable
    String getTaskKey();

    /**
     * Removes timestamp value from SharedPrefs
     */
    void removeStartTimestamp();

    /**
     * Removes task id value from SharedPrefs
     */
    void removeTaskId();

    /**
     * Removes task id value from SharedPrefs
     */
    void removeTaskKey();
}
