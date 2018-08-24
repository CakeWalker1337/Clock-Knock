package com.saritasa.clock_knock.features.authorization.domain;

import android.util.Log;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.authorization.data.AuthRepository;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * An implementation interactor class for executing domain data working methods
 */
public class AuthInteractorImpl extends BaseInteractorImpl<AuthRepository> implements AuthInteractor{

    private AuthRepository mAuthRepository;

    /**
     * @param aAuthRepository Auth repository interface
     */
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

        String verificationToken = pieces[0];

        Timber.d("Verification Token: " + verificationToken);

        mAuthRepository.saveSecretToken(verificationToken);

        return mAuthRepository.getAccessToken(verificationToken);
    }

    @Override
    public void saveAccessToken(final String aAccessToken){
        mAuthRepository.saveAccessToken(aAccessToken);
    }

}
