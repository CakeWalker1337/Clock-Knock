package com.saritasa.clock_knock.features.session.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.session.data.SessionRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for providing the Session repository interfaces
 */
@Module
public class SessionModule{

    /**
     * Provides the Global repository interface
     *
     * @param aPreferenceManager Preference manager
     * @return Global repository object
     */
    @NonNull
    @Provides
    @Singleton
    SessionRepository provideSessionRepository(@NonNull PreferenceManager aPreferenceManager){
        return new SessionRepositoryImpl(aPreferenceManager);
    }
}
