package com.saritasa.clock_knock.features.login.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.login.data.LoginRepository;
import com.saritasa.clock_knock.features.login.presentation.LoginMapper;
import com.saritasa.clock_knock.features.login.presentation.Username;
import com.saritasa.clock_knock.features.session.data.SessionRepository;

import io.reactivex.Single;

/**
 * An implementation interactor class for executing domain data working methods
 */
public class LoginInteractorImpl extends BaseInteractorImpl<LoginRepository> implements LoginInteractor{

    private LoginRepository mLoginRepository;

    private SessionRepository mSessionRepository;

    /**
     * @param aLoginRepository Login repository
     */
    public LoginInteractorImpl(@NonNull final LoginRepository aLoginRepository, @NonNull SessionRepository aSessionRepository){
        super(aLoginRepository);

        mSessionRepository = aSessionRepository;
        mLoginRepository = aLoginRepository;
    }

    @NonNull
    @Override
    public Single<Username> getUsername(){
        return mLoginRepository.getUsername().map(LoginMapper::mapUsernameFromDomain);
    }

    @Override
    public void saveUsername(@NonNull final String aUsername){
        mSessionRepository.saveUsername(aUsername);
    }

    @Override
    public boolean isAccessTokenExist(){
        return mSessionRepository.getAccessToken() != null;
    }

}
