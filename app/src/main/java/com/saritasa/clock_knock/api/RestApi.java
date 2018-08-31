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
    @NonNull
    @GET("/rest/api/2/issue/{issue}/worklog")
    Single<Response<WorklogResponseEntity>> getTaskWorkLog(@NonNull @Path("issue") String aTaskKey);

    /**
     * Creates worklog on JIRA server.
     *
     * @param aTaskKey task key (ex: MISC-303)
     * @param aBody JSON string with params of worklog for create.
     * @return Created worklog.
     */
    @NonNull
    @POST("/rest/api/2/issue/{issue}/worklog")
    Single<Response<WorklogInputEntity>> addWorklog(@NonNull @Path("issue") String aTaskKey, @NonNull @Body String aBody);

    /**
     * Updates worklog on JIRA server.
     *
     * @param aTaskKey task key (ex: MISC-303)
     * @param aWorklogId worklog id
     * @param aBody JSON string with params of worklog for update.
     * @return Response with info about request.
     */
    @NonNull
    @PUT("/rest/api/secure/issue/{issue}/worklog/{id}")
    Single<Response<ResponseBody>> updateWorklog(@NonNull @Path("issue") String aTaskKey, @NonNull @Path("id") String aWorklogId, @NonNull @Body String aBody);

    /**
     * Gets username of current user
     *
     * @return Parsed username of current user
     */
    @NonNull
    @GET("rest/api/2/myself")
    Single<UsernameEntity> getUsername();
}
