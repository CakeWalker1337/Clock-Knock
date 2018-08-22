package com.saritasa.clock_knock.base.di;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.base.data.PreferenceManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = AppModule.class)
public class ApiClientModule{

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(PreferenceManager aPreferenceManager, Context aContext){

        Interceptor headerInterceptor = aChain -> {
            Request request1 = aChain.request();
            String accessToken = aPreferenceManager.getAccessToken();
            if(accessToken != null){

                //TODO: Change this to parameters intercepting from JiraOAuthClient
                Request request = request1.newBuilder()
                        .header("Authorization", "Bearer " + accessToken)
                        .method(request1.method(), request1.body())
                        .build();
                return aChain.proceed(request);
            }
            return aChain.proceed(request1);
        };

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .addInterceptor(new ChuckInterceptor(aContext))
                .addInterceptor(headerInterceptor);

        return mClientBuilder.build();
    }

}
