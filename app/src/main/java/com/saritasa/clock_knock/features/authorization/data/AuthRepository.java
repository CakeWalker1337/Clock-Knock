package com.saritasa.clock_knock.features.authorization.data;

import com.saritasa.clock_knock.base.data.BaseRepository;

import io.reactivex.Single;

/**
 * An interface for providing auth methods to repository object
 */
public interface AuthRepository extends BaseRepository{

    /**
     * Gets the auth page URL string
     *
     * @return Auth page URL string
     */
    Single<String> getAuthPageUrl();

    /**
     * Gets access token string by verification token
     *
     * @param verificationToken Verification token string
     * @return Access token string
     */
    Single<String> getAccessToken(String verificationToken);

    /**
     * Saves the access token string to storage
     *
     * @param aAccessToken Access token string
     */
    void saveAccessToken(String aAccessToken);

    /**
     * Saves the secret token string to storage
     *
     * @param aSecretToken Secret token string
     */
    void saveSecretToken(String aSecretToken);
}
