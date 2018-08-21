package com.saritasa.clock_knock.features.login.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.login.data.LoginRepository;

public class LoginInteractorImpl extends BaseInteractorImpl<LoginRepository> implements LoginInteractor{

    public LoginInteractorImpl(final LoginRepository aRepository){
        super(aRepository);
    }

}
