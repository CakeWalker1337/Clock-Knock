package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Repository class for getting and mapping objects comes from API.
 */
public class WorklogRepositoryImpl extends BaseRepositoryImpl implements WorklogRepository{

    RestApi mRestApi;
    SessionRepository mSessionRepository;

    /**
     * @param aResourceManager provided resource manager object.
     * @param aRestApi provided api object.
     */
    public WorklogRepositoryImpl(@NonNull ResourceManager aResourceManager, @NonNull RestApi aRestApi, SessionRepository aSessionRepository){
        super(aResourceManager);
        mRestApi = aRestApi;
        mSessionRepository = aSessionRepository;
    }

    @NonNull
    @Override
    public Observable<WorklogDomain> loadWorklog(@NonNull final String aTaskKey){
        return
                mRestApi.getTaskWorkLog(aTaskKey)
                        .map(aWorklogResponseEntityResponse -> {
                            if(!aWorklogResponseEntityResponse.isSuccessful()){
                                throw new Exception("Error while fetching worklog list.");
                            }
                            return aWorklogResponseEntityResponse.body().getWorklogs();
                        })
                        .flatMapObservable(Observable::fromIterable)
                        .map(WorklogEntityMapper::mapWorklogEntityToWorklogDomain);
    }

    @NonNull
    @Override
    public Single<WorklogDomain> createWorklog(@NonNull String aTaskKey, @NonNull final WorklogDomain aWorklogDomain){
        WorklogOutputEntity worklogOutputEntity = WorklogEntityMapper.mapWorklogEntityFromWorklogDomain(aWorklogDomain);
        String jsonObject = WorklogEntityMapper.mapWorklogEntityToJsonObject(worklogOutputEntity);
        return
                mRestApi.addWorklog(aTaskKey, jsonObject)
                        //getTestApiWorklogInput(worklogOutputEntity)
                        .map(aResponseEntityResponse -> {
                            if(!aResponseEntityResponse.isSuccessful()){
                                throw new Exception("Error while fetching worklog list.");
                            }
                            return aResponseEntityResponse.body();
                        }).map(WorklogEntityMapper::mapWorklogEntityToWorklogDomain);

    }

    @NonNull
    @Override
    public void saveWorklog(@NonNull final String aTaskKey, @NonNull final WorklogDomain aWorklogDomain){
        WorklogOutputEntity worklogOutputEntity = WorklogEntityMapper.mapWorklogEntityFromWorklogDomain(aWorklogDomain);
        String jsonObject = WorklogEntityMapper.mapWorklogEntityToJsonObject(worklogOutputEntity);
        Timber.d("Sending json: " + jsonObject);
        Single<Response<ResponseBody>> single = mRestApi.updateWorklog(aTaskKey, worklogOutputEntity.getId(), jsonObject);
        single.subscribe(aResponseBodyResponse -> {
            if(aResponseBodyResponse.isSuccessful()){
                Timber.d("Success");
            } else{
                Timber.d("Error " + aResponseBodyResponse.code());
            }
        }, Timber::e);
    }

    @NonNull
    @Override
    public String getUsername(){
        return mSessionRepository.getUsername();
    }

    /**
     * Test method for creating fake response of API for test saving worklog function. Will be deprecated after successful executing of api query.
     *
     * @param aWorklogOutputEntity worklog entity object for test response.
     * @return fake response from API.
     */
    @NonNull
    public Single<Response<WorklogInputEntity>> getTestApiWorklogInput(@NonNull WorklogOutputEntity aWorklogOutputEntity){

        Timber.d("Creating fake test worklog input.");

        WorklogAuthorEntity worklogAuthorEntity = new WorklogAuthorEntity();
        worklogAuthorEntity.setKey("maxim.kovalev");

        WorklogInputEntity worklogInputEntity = new WorklogInputEntity();
        worklogInputEntity.setId("6");
        worklogInputEntity.setComment(aWorklogOutputEntity.getComment());
        worklogInputEntity.setCreationDate("2018-08-26T14:06:11.886+0000");
        int seconds = aWorklogOutputEntity.getTimeSpentSeconds();
        worklogInputEntity.setTimeSpent(seconds / 3600 + "h " + (seconds % 3600) / 60 + "m");
        worklogInputEntity.setTimeSpentSeconds(seconds + "");
        worklogInputEntity.setAuthor(worklogAuthorEntity);
        Response<WorklogInputEntity> response = Response.success(worklogInputEntity);
        return Single.just(response);
    }

    /**
     * Test method for creating fake response of API. Will be deprecated after successful executing of api query.
     *
     * @return fake response from api.
     */
    @NonNull
    public Single<Response<WorklogResponseEntity>> getTestApiWorklog(){

        Timber.d("Creating fake test worklog.");

        ArrayList<WorklogInputEntity> arrayList = new ArrayList<>();

        WorklogResponseEntity worklogResponseEntity = new WorklogResponseEntity();
        WorklogAuthorEntity worklogAuthorEntity = new WorklogAuthorEntity();
        worklogAuthorEntity.setKey("maxim.kovalev");
        for(int i = 0; i < 5; i++){

            WorklogInputEntity worklogInputEntity = new WorklogInputEntity();
            worklogInputEntity.setId("" + i);
            worklogInputEntity.setComment("Test worklog " + i);
            worklogInputEntity.setCreationDate("2018-08-2" + i + "T14:06:11.886+0000");
            worklogInputEntity.setTimeSpent(i + "h 20m");
            worklogInputEntity.setTimeSpentSeconds("" + (i * 3600 + 1200));
            worklogInputEntity.setAuthor(worklogAuthorEntity);
            arrayList.add(worklogInputEntity);
        }
        worklogResponseEntity.setWorklogs(arrayList);
        Response<WorklogResponseEntity> response = Response.success(worklogResponseEntity);
        return Single.just(response);
    }
}
