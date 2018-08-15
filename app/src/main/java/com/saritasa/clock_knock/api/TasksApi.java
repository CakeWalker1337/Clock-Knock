package com.saritasa.clock_knock.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

public interface TasksApi{

    @GET
    Observable<Response<ResponseBody>> getTasks();

    @GET
    Observable<Response<ResponseBody>> getTaskWorkLog();

}
