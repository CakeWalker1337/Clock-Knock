package com.saritasa.clock_knock.features.authorization.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.authorization.data.AuthRepository;
import com.saritasa.clock_knock.features.authorization.data.AuthRepositoryImpl;
import com.saritasa.clock_knock.features.authorization.data.JiraOAuthClient;
import com.saritasa.clock_knock.features.authorization.domain.AuthInteractor;
import com.saritasa.clock_knock.features.authorization.domain.AuthInteractorImpl;
import com.saritasa.clock_knock.features.authorization.presentation.AuthPresenter;
import com.saritasa.clock_knock.features.authorization.presentation.AuthPresenterImpl;

import dagger.Module;
import dagger.Provides;

@AuthScope
@Module
public class AuthModule{

    @NonNull
    @AuthScope
    @Provides
    public JiraOAuthClient providesJiraOAuthClient(){
        return new JiraOAuthClient();
    }

    @NonNull
    @AuthScope
    @Provides
    public AuthPresenter providesPresenter(AuthInteractor aAuthInteractor){
        return new AuthPresenterImpl(aAuthInteractor);
    }

    @NonNull
    @AuthScope
    @Provides
    public AuthInteractor providesInteractor(AuthRepository aAuthRepository) {
        return new AuthInteractorImpl(aAuthRepository);
    }

    @NonNull
    @AuthScope
    @Provides
    public AuthRepository providesRepository(ResourceManager aResourceManager, PreferenceManager aPreferenceManager, JiraOAuthClient aJiraOAuthClient) {
        return new AuthRepositoryImpl(aResourceManager, aPreferenceManager, aJiraOAuthClient);
    }
}
