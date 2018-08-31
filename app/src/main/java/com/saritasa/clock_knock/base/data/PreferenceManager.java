package com.saritasa.clock_knock.base.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An interface for providing the operations with SharedPreferences class
 */
public interface PreferenceManager{

    /**
     * Saves the access token into SharedPreferences storage
     *
     * @param aAccessToken Access token string
     */
    void saveAccessToken(@NonNull String aAccessToken);

    /**
     * Gets the access token
     *
     * @return Access token string
     */
    @Nullable
    String getAccessToken();

    /**
     * Saves the username
     *
     * @param aUsername Username string
     */
    void saveUsername(@NonNull String aUsername);

    /**
     * Gets the username
     *
     * @return Username string
     */
    @Nullable
    String getUsername();

    /**
     * Clears all data in the storage
     */
    void clearAllData();

    /**
     * Saves the secret token
     *
     * @param aSecretToken Secret token string
     */
    void saveSecretToken(@NonNull String aSecretToken);

    /**
     * Gets the secret token
     *
     * @return Secret token string
     */
    @Nullable
    String getSecretToken();

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
     * Removes timestamp value from SharedPrefs
     */
    void removeStartTimestamp();

    /**
     * Removes task id value from SharedPrefs
     */
    void removeTaskId();
}
