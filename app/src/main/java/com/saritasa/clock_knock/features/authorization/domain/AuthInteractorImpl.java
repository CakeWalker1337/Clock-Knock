package com.saritasa.clock_knock.features.authorization.domain;

import android.util.Log;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.authorization.data.AuthRepository;

import io.reactivex.Single;

public class AuthInteractorImpl extends BaseInteractorImpl<AuthRepository> implements AuthInteractor{

    private AuthRepository mAuthRepository;
    public AuthInteractorImpl(final AuthRepository aAuthRepository){
        super(aAuthRepository);
        mAuthRepository = aAuthRepository;
    }

    @Override
    public Single<String> getAuthPage(){

        return mAuthRepository.getAuthPageUrl();
    }

    @Override
    public Single<String> finishAuthentication(final String aPage){
        String[] pieces = aPage.split("\'");

        String verificationToken = pieces[1];

        Log.w("Auth", "Verification Token: " + verificationToken);

        return mAuthRepository.getAccessToken(verificationToken);
    }

    @Override
    public void saveAccessToken(final String aAccessToken){
        mAuthRepository.saveAccessToken(aAccessToken);
    }

}
