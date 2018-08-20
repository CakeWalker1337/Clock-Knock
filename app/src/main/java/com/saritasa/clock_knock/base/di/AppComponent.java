package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.features.authorization.di.AuthComponent;

import dagger.Component;

@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent{

    @NonNull
    AuthComponent.Builder authComponentBuilder();

    void inject(App aApp);
}
