package com.saritasa.clock_knock.features.auth.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClient;
import com.saritasa.clock_knock.features.auth.data.AuthRepository;
import com.saritasa.clock_knock.features.auth.data.AuthRepositoryImpl;
import com.saritasa.clock_knock.features.auth.domain.AuthInteractor;
import com.saritasa.clock_knock.features.auth.domain.AuthInteractorImpl;
import com.saritasa.clock_knock.features.auth.presentation.AuthPresenter;
import com.saritasa.clock_knock.features.auth.presentation.AuthPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * A class for providing the necessary objects from Auth module
 */
@AuthScope
@Module
public class AuthModule{

    /**
     * Provides the Auth presenter
     *
     * @param aAuthInteractor Auth interactor object
     * @return Auth presenter object
     */
    @NonNull
    @AuthScope
    @Provides
    public AuthPresenter providesPresenter(@NonNull AuthInteractor aAuthInteractor){
        return new AuthPresenterImpl(aAuthInteractor);
    }

    /**
     * Provides the Auth interactor
     *
     * @param aAuthRepository Auth repository object
     * @return Auth interactor object
     */
    @NonNull
    @AuthScope
    @Provides
    public AuthInteractor providesInteractor(@NonNull AuthRepository aAuthRepository, @NonNull SessionRepository aSessionRepository) {
        return new AuthInteractorImpl(aAuthRepository, aSessionRepository);
    }

    /**
     * Provides the Auth repository
     *
     * @param aResourceManager Resource manager object
     * @param aJiraOAuthClient Jira OAuth client object
     * @return Auth repository object
     */
    @NonNull
    @AuthScope
    @Provides
    public AuthRepository providesRepository(@NonNull ResourceManager aResourceManager,
                                             @NonNull JiraOAuthClient aJiraOAuthClient) {
        return new AuthRepositoryImpl(aResourceManager, aJiraOAuthClient);
    }
}
