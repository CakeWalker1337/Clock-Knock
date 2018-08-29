package com.saritasa.clock_knock.features.auth.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    @NonNull
    Single<String> getAuthPageUrl();

    /**
     * Gets access token string by verification token
     *
     * @param aVerificationToken Verification token string
     * @return Access token string
     */
    @NonNull
    Single<String> getAccessToken(@NonNull String aVerificationToken);
}
