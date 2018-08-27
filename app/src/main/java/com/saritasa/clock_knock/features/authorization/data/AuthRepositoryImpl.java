package com.saritasa.clock_knock.features.authorization.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.GlobalRepository;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClient;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClientImpl;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * An implementation repository class for executing data working methods
 */
public class AuthRepositoryImpl extends BaseRepositoryImpl implements AuthRepository{

    private JiraOAuthClient mJiraOAuthClient;
    private GlobalRepository mGlobalRepository;
    private ResourceManager mResourceManager;

    /**
     *
     * @param aResourceManager Resource manager
     * @param aGlobalRepository Global repository
     * @param aJiraOAuthClient Jira OAuth client
     */
    public AuthRepositoryImpl(@NonNull ResourceManager aResourceManager,
                              @NonNull GlobalRepository aGlobalRepository,
                              @NonNull JiraOAuthClient aJiraOAuthClient){
        super(aResourceManager);

        mResourceManager = aResourceManager;
        mGlobalRepository = aGlobalRepository;
        mJiraOAuthClient = aJiraOAuthClient;

    }

    @NonNull
    @Override
    public Single<String> getAuthPageUrl(){

        return Single.just("")
                .observeOn(Schedulers.computation())
                .map(aUrl -> {
                    String temporaryToken = mJiraOAuthClient.getTemporaryToken();
                    return mJiraOAuthClient.getAuthorizationUrl(temporaryToken);
                });
    }

    @NonNull
    @Override
    public Single<String> getAccessToken(@NonNull String verificationToken){
        return Single.just("")
                .subscribeOn(Schedulers.computation())
                .map(aAccessToken -> mJiraOAuthClient.getAccessToken(verificationToken));
    }

    @Override
    public void saveAccessToken(@NonNull final String aAccessToken){
        mGlobalRepository.saveAccessToken(aAccessToken);
    }

    @Override
    public void saveSecretToken(@NonNull final String aSecretToken){
        mGlobalRepository.saveSecretToken(aSecretToken);
    }
}
