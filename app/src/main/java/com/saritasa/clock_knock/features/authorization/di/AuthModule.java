package com.saritasa.clock_knock.features.authorization.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.GlobalRepository;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClient;
import com.saritasa.clock_knock.features.authorization.data.AuthRepository;
import com.saritasa.clock_knock.features.authorization.data.AuthRepositoryImpl;
import com.saritasa.clock_knock.base.network.oauth.JiraOAuthClientImpl;
import com.saritasa.clock_knock.features.authorization.domain.AuthInteractor;
import com.saritasa.clock_knock.features.authorization.domain.AuthInteractorImpl;
import com.saritasa.clock_knock.features.authorization.presentation.AuthPresenter;
import com.saritasa.clock_knock.features.authorization.presentation.AuthPresenterImpl;

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
    public AuthPresenter providesPresenter(AuthInteractor aAuthInteractor){
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
    public AuthInteractor providesInteractor(AuthRepository aAuthRepository) {
        return new AuthInteractorImpl(aAuthRepository);
    }

    /**
     * Provides the Auth repository
     *
     * @param aResourceManager Resource manager object
     * @param aGlobalRepository Global repository object
     * @param aJiraOAuthClient Jira OAuth client object
     * @return Auth repository object
     */
    @NonNull
    @AuthScope
    @Provides
    public AuthRepository providesRepository(ResourceManager aResourceManager, GlobalRepository aGlobalRepository, JiraOAuthClient aJiraOAuthClient) {
        return new AuthRepositoryImpl(aResourceManager, aGlobalRepository, aJiraOAuthClient);
    }
}
