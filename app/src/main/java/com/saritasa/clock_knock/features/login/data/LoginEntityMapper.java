package com.saritasa.clock_knock.features.login.data;

import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

public final class LoginEntityMapper{

    public static UsernameDomain mapUsernameFromEntity(UsernameEntity aUsernameEntity) {
        return new UsernameDomain(aUsernameEntity.getKey());
    }

    public static UsernameEntity mapUsernameToEntity(UsernameDomain aUsernameDomain) {
        return new UsernameEntity(aUsernameDomain.getUsername());
    }
}
