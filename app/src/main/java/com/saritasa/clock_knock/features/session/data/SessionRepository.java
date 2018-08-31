package com.saritasa.clock_knock.features.session.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An interface to provide methods working with global data storages like BuildConfig, SharedPreferences, etc.
 */
public interface SessionRepository{

    /**
     * Saves the access token
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
     * Gets the base URL
     *
     * @return Base URL string
     */
    @NonNull
    String getBaseUrl();

    /**
     * Gets the private key
     *
     * @return Private key string
     */
    @NonNull
    String getPrivateKey();

    /**
     * Gets the consumer key
     *
     * @return Consumer key string
     */
    @NonNull
    String getConsumerKey();

    /**
     * Gets the network timeout
     *
     * @return Network timeout
     */
    @NonNull
    long getNetworkTimeout();

    /**
     * Gets the connect timeout
     *
     * @return Connect timeout
     */
    @NonNull
    long getConnectTimeout();

    /**
     * Gets the count of request retries before the request rejecting
     *
     * @return Count of request retries
     */
    @NonNull
    int getRetryRequestCount();
}
