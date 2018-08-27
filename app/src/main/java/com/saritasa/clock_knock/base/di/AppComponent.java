package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.features.auth.di.AuthComponent;
import com.saritasa.clock_knock.features.login.di.LoginComponent;
import com.saritasa.clock_knock.features.main.di.MainComponent;
import com.saritasa.clock_knock.features.session.di.SessionModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main component contains feature subcomponents and API and App modules
 */
@Singleton
@Component(modules = {ApiModule.class, AppModule.class, SessionModule.class})
public interface AppComponent{

    /**
     * Gets the Auth component builder
     *
     * @return Auth component builder
     */
    @NonNull
    AuthComponent.Builder authComponentBuilder();

    /**
     * Gets the Main component builder
     *
     * @return Main component builder
     */
    @NonNull
    MainComponent.Builder mainComponentBuilder();

    /**
     * Gets the Login component builder
     *
     * @return Login component builder
     */
    @NonNull
    LoginComponent.Builder loginComponentBuilder();

    /**
     * Injects App component to the App class
     * @param aApp App class object
     */
    void inject(App aApp);
}
