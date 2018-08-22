package com.saritasa.clock_knock.features.login.data;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import io.reactivex.Single;

public interface LoginRepository extends BaseRepository{

    Single<UsernameDomain> getUsername();

    void saveUsername(String aUsername);

    boolean isAccessTokenExist();
}
