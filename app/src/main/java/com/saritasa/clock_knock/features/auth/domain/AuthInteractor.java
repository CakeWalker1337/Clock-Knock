package com.saritasa.clock_knock.features.auth.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.util.Strings;

import io.reactivex.Single;

/**
 * An interface for providing the interactor methods
 */
public interface AuthInteractor extends BaseInteractor {

    /**
     * Requests the auth page URL string from repository
     *
     * @return The auth page URL string single
     */
    @NonNull
    Single<String> getAuthPage();

    /**
     * Gets the verification token from page, saves it and requests the access token from repository
     *
     * @param aPage Page markup substring with the verification code
     * @return Access token string single
     */
    @NonNull
    Single<String> finishAuthentication(String aPage);

    /**
     * Requests repository to save the access token
     *
     * @param aAccessToken Access token string
     */
    void saveAccessToken(@NonNull String aAccessToken);
}
