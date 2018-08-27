package com.saritasa.clock_knock.api;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.data.TasksResponseEntity;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api interface interacts with JIRA API.
 */
public interface RestApi{

    /**
     * Gets tasks from JIRA API by username of REGISTERED AND AUTHORIZED user.
     *
     * @param aJqlQuery query on JQL query language.
     * @return Single object with response entity.
     */
    @NonNull
    @GET("/rest/api/2/search")
    Single<Response<TasksResponseEntity>> getTasks(@NonNull @Query("jql") String aJqlQuery);

    /**
     * Gets worklogs of task.
     * @return Observable with worklogs of task.
     */
    @NonNull
    @GET
    Single<Response<ResponseBody>> getTaskWorkLog();

}
