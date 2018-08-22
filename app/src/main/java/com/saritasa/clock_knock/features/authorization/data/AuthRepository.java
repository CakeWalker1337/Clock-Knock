package com.saritasa.clock_knock.features.authorization.data;

import com.saritasa.clock_knock.base.data.BaseRepository;

import io.reactivex.Single;

public interface AuthRepository extends BaseRepository{

    Single<String> getAuthPageUrl();

    Single<String> getAccessToken(String verificationToken);

    void saveAccessToken(String aAccessToken);

    void saveSecretToken(String aSecretToken);
}
