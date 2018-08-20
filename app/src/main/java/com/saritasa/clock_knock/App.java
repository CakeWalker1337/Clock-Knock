package com.saritasa.clock_knock;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.di.ApiModule;
import com.saritasa.clock_knock.base.di.AppComponent;
import com.saritasa.clock_knock.base.di.AppModule;
import com.saritasa.clock_knock.base.di.DaggerAppComponent;

import timber.log.Timber;

public class App extends Application{

    AppComponent mAppComponent;

    @NonNull
    public static App get(@NonNull Context aContext){
        return (App) aContext.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        mAppComponent = buildAppComponent();
        mAppComponent.inject(this);
    }

    protected AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
