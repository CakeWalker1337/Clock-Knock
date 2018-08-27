package com.saritasa.clock_knock;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.di.ApiModule;
import com.saritasa.clock_knock.base.di.AppComponent;
import com.saritasa.clock_knock.base.di.AppModule;
import com.saritasa.clock_knock.base.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * A custom Application class
 */
public class App extends Application{

    AppComponent mAppComponent;

    /**
     * Gets App class instance from context
     *
     * @param aContext Context
     * @return App object instance
     */
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

    /**
     * Builds App Component interface for Dagger
     *
     * @return AppComponent interface
     */
    @NonNull
    protected AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    /**
     * Gets AppComponent instance
     *
     * @return AppComponent interface instance
     */
    @NonNull
    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
