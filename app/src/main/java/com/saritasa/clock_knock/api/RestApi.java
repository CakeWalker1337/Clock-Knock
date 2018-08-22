package com.saritasa.clock_knock.api;

import com.saritasa.clock_knock.features.tasks.data.TasksResponseEntity;

import io.reactivex.Observable;
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
     * @param jqlQuery query on JQL query language.
     * @return Single object with response entity.
     */
    @GET("/rest/api/2/search")
    Single<TasksResponseEntity> getTasks(@Query("jql") String jqlQuery);

    /**
     * Gets worklogs of task.
     * @return Observable with worklogs of task.
     */
    @GET
    Observable<Response<ResponseBody>> getTaskWorkLog();

}
