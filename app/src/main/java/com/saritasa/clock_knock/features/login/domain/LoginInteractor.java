package com.saritasa.clock_knock.features.login.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.features.login.presentation.Username;

import io.reactivex.Single;

/**
 * An interface for providing the interactor methods
 */
public interface LoginInteractor extends BaseInteractor{

    /**
     * Requests the username from repository
     *
     * @return Username object single
     */
    @NonNull
    Single<Username> getUsername();

    /**
     * Requests repository to save the username string
     *
     * @param aUsername Username string
     */
    void saveUsername(@NonNull String aUsername);

    /**
     * Requests repository to check the access token existence
     *
     * @return true if access token exists in storage, false otherwise
     */
    boolean isAccessTokenExist();
}
