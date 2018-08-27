package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.api.RestApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * DI api module class. Includes API client module.
 */
@Module(includes = {ApiClientModule.class})
public class ApiModule{

    /**
     * Provides Retrofit builder. Singleton
     * @param aOkHttpClient client for creating Retrofit builder.
     * @return retrofit builder object.
     */
    @NonNull
    @Provides
    @Singleton
    public Retrofit provideRetrofitBuilder(@NonNull OkHttpClient aOkHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder();

        return builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(aOkHttpClient)
                .build();
    }

    /**
     * Provides RestApi object. Singleton.
     * @param aRetrofit Retrofit instance for creating RestApi object.
     * @return RestApi object.
     */
    @NonNull
    @Provides
    @Singleton
    public RestApi provideRestApi(@NonNull Retrofit aRetrofit){
        return aRetrofit.create(RestApi.class);
    }
}
