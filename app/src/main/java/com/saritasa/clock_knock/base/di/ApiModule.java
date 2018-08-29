package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.network.exception.RxErrorHandlingCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A module for providing the Api interface and builder
 */
@Module(includes = {ApiClientModule.class})
public class ApiModule{

    /**
     * Provides the builder for creating Retrofit API interface
     *
     * @param aOkHttpClient OkHttp client
     * @return Builder for creating the Retrofit API interface
     */
    @NonNull
    @Provides
    @Singleton
    public Retrofit provideRetrofitBuilder(@NonNull OkHttpClient aOkHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder();

        return builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(aOkHttpClient)
                .build();
    }

    /**
     * Provides the Retrofit API interface
     *
     * @param aBuilder Retrofit builder
     * @return Retrofit API interface
     */
    @NonNull
    @Provides
    @Singleton
    public RestApi provideRestApi(@NonNull Retrofit aBuilder){
        return aBuilder.create(RestApi.class);
    }
}
