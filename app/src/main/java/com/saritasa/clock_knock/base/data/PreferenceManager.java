package com.saritasa.clock_knock.base.data;

/**
 * An interface for providing the operations with SharedPreferences class
 */
public interface PreferenceManager{

    /**
     * Saves the access token into SharedPreferences storage
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

}
