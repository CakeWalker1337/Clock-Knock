package com.saritasa.clock_knock.features.login.data;

import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

/**
 * A final class for converting entity objects to domain objects
 */
public final class LoginEntityMapper{

    /**
     * Converts entity username object to domain username object
     *
     * @param aUsernameEntity Entity username object
     * @return New domain username object
     */
    public static UsernameDomain mapUsernameFromEntity(UsernameEntity aUsernameEntity) {
        return new UsernameDomain(aUsernameEntity.getKey());
    }

    /**
     * Converts domain username object to entity username object
     *
     * @param aUsernameDomain Domain username object
     * @return New entity username object
     */
    public static UsernameEntity mapUsernameToEntity(UsernameDomain aUsernameDomain) {
        return new UsernameEntity(aUsernameDomain.getUsername());
    }
}
