package com.saritasa.clock_knock.features.login.presentation;

import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

public final class LoginMapper{

    public static Username mapUsernameFromDomain(UsernameDomain aUsernameDomain){
        return new Username(aUsernameDomain.getUsername());
    }

    public static UsernameDomain mapUsernameToDomain(Username aUsername){
        return new UsernameDomain(aUsername.getUsername());
    }
}
