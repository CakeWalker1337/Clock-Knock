package com.saritasa.clock_knock.base.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.GlobalRepository;
import com.saritasa.clock_knock.base.data.GlobalRepositoryImpl;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.PreferenceManagerImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.data.ResourceManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for providing the Resource manager, Preference manager and Global repository interfaces
 */
@Module
public class AppModule{

    private Context mContext;

    /**
     * @param aContext Context
     */
    public AppModule(Context aContext){
        mContext = aContext;
    }

    /**
     * Provides the context
     *
     * @return Context
     */
    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    /**
     * Provides the resource manager interface
     *
     * @param aContext Context
     * @return Resource manager object
     */
    @NonNull
    @Provides
    @Singleton
    ResourceManager provideResourceManager(@NonNull Context aContext){
        return new ResourceManagerImpl(aContext);
    }

    /**
     * Provides the preference manager interface
     *
     * @param aContext Context
     * @return Preference manager object
     */
    @NonNull
    @Provides
    @Singleton
    PreferenceManager providePreferenceManager(@NonNull Context aContext){
        return new PreferenceManagerImpl(aContext);
    }

    /**
     * Provides the Global repository interface
     *
     * @param aPreferenceManager Preference manager
     * @return Global repository object
     */
    @NonNull
    @Provides
    @Singleton
    GlobalRepository provideGlobalRepository(@NonNull PreferenceManager aPreferenceManager){
        return new GlobalRepositoryImpl(aPreferenceManager);
    }
}
