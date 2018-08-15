package com.saritasa.clock_knock.base.di;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.network.ApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule{

    @Provides
    @Singleton
    public RestApi provideRestApi(ApiClient aApiClient){
        return aApiClient.createApi();
    }
}
