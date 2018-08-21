package com.saritasa.clock_knock.features.authorization.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class AuthRepositoryImpl extends BaseRepositoryImpl implements AuthRepository{

    private JiraOAuthClient mJiraOAuthClient;
    private PreferenceManager mPreferenceManager;
    private ResourceManager mResourceManager;

    /**
     * @param aResourceManager resource manager
     */
    public AuthRepositoryImpl(@NonNull ResourceManager aResourceManager,
                              @NonNull PreferenceManager aPreferenceManager,
                              @NonNull JiraOAuthClient aJiraOAuthClient){
        super(aResourceManager);

        mResourceManager = aResourceManager;
        mPreferenceManager = aPreferenceManager;
        mJiraOAuthClient = aJiraOAuthClient;
    }

    @Override
    public Single<String> getAuthPageUrl(){

        return Single.just("")
                .subscribeOn(Schedulers.computation())
                .map(aUrl -> mJiraOAuthClient.getAuthorizationUrl());
    }

    @Override
    public Single<String> getAccessToken(String verificationToken){
        return Single.just("")
                .subscribeOn(Schedulers.computation())
                .map(aAccessToken -> mJiraOAuthClient.getAccessToken(verificationToken));
    }

    @Override
    public void saveAccessToken(final String aAccessToken){
        mPreferenceManager.saveAccessToken(aAccessToken);
    }
}
