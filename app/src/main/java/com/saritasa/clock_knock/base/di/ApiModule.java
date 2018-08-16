package com.saritasa.clock_knock.base.di;

import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.api.RestApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ApiClientModule.class})
public class ApiModule{

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient aOkHttpClient){
        Retrofit.Builder builder = new Retrofit.Builder();

        return builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(aOkHttpClient);
    }

    @Provides
    @Singleton
    public RestApi provideRestApi(Retrofit.Builder aBuilder){
        return aBuilder.build().create(RestApi.class);
    }
}
