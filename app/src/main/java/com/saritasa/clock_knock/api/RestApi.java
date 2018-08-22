package com.saritasa.clock_knock.api;

import com.saritasa.clock_knock.features.login.data.UsernameEntity;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi{

    @GET
    Observable<Response<ResponseBody>> getTasks();

    @GET
    Observable<Response<ResponseBody>> getTaskWorkLog();

    @GET("rest/api/2/myself")
    Single<UsernameEntity> getUsername();
}
