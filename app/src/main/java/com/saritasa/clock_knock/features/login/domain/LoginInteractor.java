package com.saritasa.clock_knock.features.login.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.features.login.presentation.Username;

import io.reactivex.Single;

public interface LoginInteractor extends BaseInteractor{

    Single<Username> getUsername();

    void saveUsername(String aUsername);

    boolean isAccessTokenExist();
}
