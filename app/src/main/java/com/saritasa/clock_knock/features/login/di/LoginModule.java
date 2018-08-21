package com.saritasa.clock_knock.features.login.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.login.data.LoginRepository;
import com.saritasa.clock_knock.features.login.data.LoginRepositoryImpl;
import com.saritasa.clock_knock.features.login.domain.LoginInteractor;
import com.saritasa.clock_knock.features.login.domain.LoginInteractorImpl;
import com.saritasa.clock_knock.features.login.presentation.LoginPresenter;
import com.saritasa.clock_knock.features.login.presentation.LoginPresenterImpl;

import dagger.Module;
import dagger.Provides;

@LoginScope
@Module
public class LoginModule{

    @NonNull
    @LoginScope
    @Provides
    public LoginPresenter providesPresenter(LoginInteractor aLoginInteractor){
        return new LoginPresenterImpl(aLoginInteractor);
    }

    @NonNull
    @LoginScope
    @Provides
    public LoginInteractor providesInteractor(LoginRepository aLoginRepository) {
        return new LoginInteractorImpl(aLoginRepository);
    }

    @NonNull
    @LoginScope
    @Provides
    public LoginRepository providesRepository(ResourceManager aResourceManager, PreferenceManager aPreferenceManager) {
        return new LoginRepositoryImpl(aResourceManager, aPreferenceManager);
    }
}