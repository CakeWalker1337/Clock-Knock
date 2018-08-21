package com.saritasa.clock_knock.features.authorization.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.util.Strings;

import io.reactivex.Single;

public interface AuthInteractor extends BaseInteractor {
    Single<String> getAuthPage();

    Single<String> finishAuthentication(String aPage);

    void saveAccessToken(String aAccessToken);
}
