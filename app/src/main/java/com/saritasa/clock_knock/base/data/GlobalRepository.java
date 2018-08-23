package com.saritasa.clock_knock.base.data;

/**
 * An interface to provide methods working with global data storages like BuildConfig, SharedPreferences, etc.
 */
public interface GlobalRepository{

    /**
     * Saves the access token
     *
     * @param aAccessToken Access token string
     */
    void saveAccessToken(String aAccessToken);

    /**
     * Gets the access token
     *
     * @return Access token string
     */
    String getAccessToken();

    /**
     * Saves the username
     *
     * @param aUsername Username string
     */
    void saveUsername(String aUsername);

    /**
     * Gets the username
     *
     * @return Username string
     */
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
    void saveSecretToken(String aSecretToken);

    /**
     * Gets the secret token
     *
     * @return Secret token string
     */
    String getSecretToken();

    /**
     * Gets the base URL
     *
     * @return Base URL string
     */
    String getBaseUrl();

    /**
     * Gets the private key
     *
     * @return Private key string
     */
    String getPrivateKey();

    /**
     * Gets the consumer key
     *
     * @return Consumer key string
     */
    String getConsumerKey();

    /**
     * Gets the network timeout
     *
     * @return Network timeout
     */
    long getNetworkTimeout();

    /**
     * Gets the connect timeout
     *
     * @return Connect timeout
     */
    long getConnectTimeout();

    /**
     * Gets the count of request retries before the request rejecting
     *
     * @return Count of request retries
     */
    int getRetryRequestCount();
}
