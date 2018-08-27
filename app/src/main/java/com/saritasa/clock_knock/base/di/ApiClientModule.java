package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * DI API client module.
 */
@Module
public class ApiClientModule{

    /**
     * Provides OkHttpClient object. Singleton.
     *
     * @return OkHttpClient object.
     */
    @NonNull
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor);

        return mClientBuilder.build();
    }

}
