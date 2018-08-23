package com.saritasa.clock_knock.features.login.data;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import io.reactivex.Single;

/**
 * An interface for providing login methods to repository object
 */
public interface LoginRepository extends BaseRepository{

    /**
     * Gets the domain username object single from entity object single
     *
     * @return Domain username object single
     */
    Single<UsernameDomain> getUsername();

    /**
     * Saves the username
     *
     * @param aUsername Username string
     */
    void saveUsername(String aUsername);

    /**
     * Checks if access token exists in storage
     *
     * @return true if token exist, false otherwise
     */
    boolean isAccessTokenExist();
}
