package com.saritasa.clock_knock.base.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.data.ResourceManagerImpl;

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

    @NonNull
    @Provides
    @Singleton
    ResourceManager provideResourceManager(@NonNull Context aContext){
        return new ResourceManagerImpl(aContext);
    }

}
