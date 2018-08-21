package com.saritasa.clock_knock.api;

import com.saritasa.clock_knock.features.tasks.data.TasksResponseEntity;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi{

    @GET("/rest/api/2/search")
    Single<TasksResponseEntity> getTasks(@Query("jql") String jqlQuery);

    @GET
    Observable<Response<ResponseBody>> getTaskWorkLog();

}
