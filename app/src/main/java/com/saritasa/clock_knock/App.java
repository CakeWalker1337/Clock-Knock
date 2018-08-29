package com.saritasa.clock_knock;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.di.ApiModule;
import com.saritasa.clock_knock.base.di.AppComponent;
import com.saritasa.clock_knock.base.di.AppModule;
import com.saritasa.clock_knock.base.di.DaggerAppComponent;
import com.saritasa.clock_knock.features.session.di.SessionModule;

import timber.log.Timber;

/**
 * Application class.
 */
public class App extends Application{

    AppComponent mAppComponent;

    /**
     * Gets application object from context.
     *
     * @param aContext context.
     * @return Application object.
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
     * Gets AppComponent object.
     *
     * @return AppComponent object.
     */
    @NonNull
    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    /**
     * Builds DI application component.
     * @return application component object.
     */
    @NonNull
    protected AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .sessionModule(new SessionModule())
                .build();
    }

}
