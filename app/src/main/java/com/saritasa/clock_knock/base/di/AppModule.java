package com.saritasa.clock_knock.base.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule{

    Context mContext;

    public AppModule(Context aContext){
        mContext = aContext;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }
}
