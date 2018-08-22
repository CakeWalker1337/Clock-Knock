package com.saritasa.clock_knock.features.login.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.login.data.LoginRepository;
import com.saritasa.clock_knock.features.login.presentation.LoginMapper;
import com.saritasa.clock_knock.features.login.presentation.Username;

import io.reactivex.Single;

public class LoginInteractorImpl extends BaseInteractorImpl<LoginRepository> implements LoginInteractor{

    private LoginRepository mLoginRepository;

    public LoginInteractorImpl(final LoginRepository aLoginRepository){
        super(aLoginRepository);

        mLoginRepository = aLoginRepository;
    }

    @Override
    public Single<Username> getUsername(){
        return mLoginRepository.getUsername().map(LoginMapper::mapUsernameFromDomain);
    }

    @Override
    public void saveUsername(final String aUsername){
        mLoginRepository.saveUsername(aUsername);
    }

    @Override
    public boolean isAccessTokenExist(){
        return mLoginRepository.isAccessTokenExist();
    }

}
