package com.saritasa.clock_knock.features.auth.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClient;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * An implementation repository class for executing data working methods
 */
public class AuthRepositoryImpl extends BaseRepositoryImpl implements AuthRepository{

    private JiraOAuthClient mJiraOAuthClient;
    private ResourceManager mResourceManager;

    /**
     * @param aResourceManager Resource manager
     * @param aJiraOAuthClient Jira OAuth client
     */
    public AuthRepositoryImpl(@NonNull ResourceManager aResourceManager,
                              @NonNull JiraOAuthClient aJiraOAuthClient){
        super(aResourceManager);

        mResourceManager = aResourceManager;
        mJiraOAuthClient = aJiraOAuthClient;

    }

    @NonNull
    @Override
    public Single<String> getAuthPageUrl(){
        return Single.fromCallable(() -> {
            String temporaryToken = mJiraOAuthClient.getTemporaryToken();
            return mJiraOAuthClient.getAuthorizationUrl(temporaryToken);
        }).subscribeOn(Schedulers.computation());
    }

    @NonNull
    @Override
    public Single<String> getAccessToken(@NonNull String aVerificationToken){
        return Single.fromCallable(() -> mJiraOAuthClient.getAccessToken(aVerificationToken))
                .subscribeOn(Schedulers.computation());
    }
}
