package com.saritasa.clock_knock.base.network;

import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.api.RestApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class ApiClient{

    Retrofit.Builder mBuilder;
    OkHttpClient.Builder mClientBuilder;

    public ApiClient(){
        mClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        mBuilder = new Retrofit
                .Builder();
    }

    public RestApi createApi(){
        return mBuilder
                .client(mClientBuilder.build())
                .build()
                .create(RestApi.class);

    }
}
