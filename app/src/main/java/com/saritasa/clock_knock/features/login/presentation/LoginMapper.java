package com.saritasa.clock_knock.features.login.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

/**
 * A final class for converting domain objects to usual objects
 */
public final class LoginMapper{

    /**
     * Converts domain username object to usual username object
     *
     * @param aUsernameDomain Domain username object
     * @return New usual username object
     */
    @NonNull
    public static Username mapUsernameFromDomain(@NonNull UsernameDomain aUsernameDomain){
        return new Username(aUsernameDomain.getUsername());
    }

    /**
     * Converts usual username object to domain username object
     *
     * @param aUsername Usual username object
     * @return New domain username object
     */
    @NonNull
    public static UsernameDomain mapUsernameToDomain(@NonNull Username aUsername){
        return new UsernameDomain(aUsername.getUsername());
    }
}
