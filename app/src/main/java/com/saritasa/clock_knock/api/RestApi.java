package com.saritasa.clock_knock.api;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.login.data.UsernameEntity;
import com.saritasa.clock_knock.features.tasks.data.TasksResponseEntity;
import com.saritasa.clock_knock.features.worklog.data.WorklogInputEntity;
import com.saritasa.clock_knock.features.worklog.data.WorklogResponseEntity;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The Retrofit API interface for throwing requests to a server
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
     *
     * @return Observable with worklogs of task.
     */
    @GET("/rest/api/2/issue/{issue}/worklog")
    Single<Response<WorklogResponseEntity>> getTaskWorkLog(@Path("issue") String aTaskKey);

    @POST("/rest/api/2/issue/{issue}/worklog")
    Single<Response<WorklogInputEntity>> addWorklog(@Path("issue") String aTaskKey, @Body String aBody);

    @PUT("/rest/api/2/issue/{issue}/worklog/{id}")
    Single<Response<ResponseBody>> updateWorklog(@Path("issue") String aTaskKey, @Path("id") String worklogId, @Body String aBody);

    /**
     * Gets username of current user
     *
     * @return Parsed username of current user
     */
    @GET("rest/api/2/myself")
    Single<UsernameEntity> getUsername();
}
