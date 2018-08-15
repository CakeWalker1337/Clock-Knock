package com.saritasa.clock_knock.base.di;

import com.saritasa.clock_knock.App;

import dagger.Component;

@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent{

    void inject(App aApp);
}
