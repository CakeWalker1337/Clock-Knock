package com.saritasa.clock_knock.features.authorization.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.authorization.data.JiraOAuthClient;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule{

    @NonNull
    @Provides
    public JiraOAuthClient providesJiraOAuthClient(){
        return new JiraOAuthClient();
    }
}
