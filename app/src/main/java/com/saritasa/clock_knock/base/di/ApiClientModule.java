package com.saritasa.clock_knock.base.di;

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
    public OkHttpClient provideOkHttpClient(PreferenceManager aPreferenceManager){

        Interceptor headerInterceptor = chain -> {
            Request original = chain.request();
            String accessToken = aPreferenceManager.getAccessToken();
            if(accessToken != null){
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer: " + accessToken)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
            return chain.proceed(original);
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder mClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headerInterceptor);

        return mClientBuilder.build();
    }

}
