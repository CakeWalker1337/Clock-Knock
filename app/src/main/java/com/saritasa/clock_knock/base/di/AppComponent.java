package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.features.authorization.di.AuthComponent;
import com.saritasa.clock_knock.features.login.di.LoginComponent;
import com.saritasa.clock_knock.features.main.di.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent{

    @NonNull
    AuthComponent.Builder authComponentBuilder();

    @NonNull
    MainComponent.Builder mainComponentBuilder();

    @NonNull
    LoginComponent.Builder loginComponentBuilder();

    void inject(App aApp);
}
