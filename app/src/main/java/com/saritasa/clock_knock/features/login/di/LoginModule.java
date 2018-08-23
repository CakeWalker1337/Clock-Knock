package com.saritasa.clock_knock.features.login.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.GlobalRepository;
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

/**
 * A class for providing the necessary objects from Login module
 */
@LoginScope
@Module
public class LoginModule{

    /**
     * Provides the Login presenter
     *
     * @param aLoginInteractor Login interactor object
     * @return Login presenter object
     */
    @NonNull
    @LoginScope
    @Provides
    public LoginPresenter providesPresenter(LoginInteractor aLoginInteractor){
        return new LoginPresenterImpl(aLoginInteractor);
    }

    /**
     * Provides the Login interactor
     *
     * @param aLoginRepository Login repository object
     * @return Login interactor object
     */
    @NonNull
    @LoginScope
    @Provides
    public LoginInteractor providesInteractor(LoginRepository aLoginRepository) {
        return new LoginInteractorImpl(aLoginRepository);
    }

    /**
     * Provides the Login repository
     *
     * @param aResourceManager Resource manager object
     * @param aGlobalRepository Global repository object
     * @param aRestApi Rest API interface
     * @return Login repository object
     */
    @NonNull
    @LoginScope
    @Provides
    public LoginRepository providesRepository(ResourceManager aResourceManager, GlobalRepository aGlobalRepository, RestApi aRestApi) {
        return new LoginRepositoryImpl(aResourceManager, aGlobalRepository, aRestApi);
    }
}