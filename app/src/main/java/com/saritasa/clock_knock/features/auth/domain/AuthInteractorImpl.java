package com.saritasa.clock_knock.features.auth.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.auth.data.AuthRepository;
import com.saritasa.clock_knock.features.session.data.SessionRepository;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * An implementation interactor class for executing domain data working methods
 */
public class AuthInteractorImpl extends BaseInteractorImpl<AuthRepository> implements AuthInteractor{

    private AuthRepository mAuthRepository;
    private SessionRepository mSessionRepository;

    /**
     * @param aAuthRepository Auth repository interface
     */
    public AuthInteractorImpl(@NonNull final AuthRepository aAuthRepository, @NonNull final SessionRepository aSessionRepository){
        super(aAuthRepository);
        mAuthRepository = aAuthRepository;
        mSessionRepository = aSessionRepository;
    }

    @NonNull
    @Override
    public Single<String> getAuthPage(){
        return mAuthRepository.getAuthPageUrl();
    }

    @NonNull
    @Override
    public Single<String> finishAuthentication(@NonNull final String aPage){
        String[] pieces = aPage.split("\'");

        String verificationToken = pieces[0];

        mSessionRepository.saveSecretToken(verificationToken);

        return mAuthRepository.getAccessToken(verificationToken);
    }

    @Override
    public void saveAccessToken(@NonNull final String aAccessToken){
        mSessionRepository.saveAccessToken(aAccessToken);
    }

}
