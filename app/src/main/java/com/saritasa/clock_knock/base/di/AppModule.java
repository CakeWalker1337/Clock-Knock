package com.saritasa.clock_knock.base.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.base.data.ResourceManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DI Application module class.
 */
@Module
public class AppModule{

    Context mContext;

    public AppModule(@NonNull Context aContext){
        mContext = aContext;
    }

    /**
     * Provides context into other classes. Singleton.
     *
     * @return provided context.
     */
    @NonNull
    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    /**
     * Provides resource manager. Singleton.
     * @param aContext context for creating resource manager.
     * @return resource manager object.
     */
    @NonNull
    @Provides
    @Singleton
    ResourceManager provideResourceManager(@NonNull Context aContext){
        return new ResourceManagerImpl(aContext);
    }

}
