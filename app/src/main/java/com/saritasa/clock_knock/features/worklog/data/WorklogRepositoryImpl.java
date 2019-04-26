package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.local.AppDatabase;
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

    private AppDatabase mAppDatabase;
    private SessionRepository mSessionRepository;

    /**
     * @param aResourceManager provided resource manager object.
     * @param aAppDatabase provided api object.
     */
    public WorklogRepositoryImpl(@NonNull ResourceManager aResourceManager, @NonNull AppDatabase aAppDatabase, @NonNull SessionRepository aSessionRepository){
        super(aResourceManager);
        mAppDatabase = aAppDatabase;
        mSessionRepository = aSessionRepository;
    }

    @NonNull
    @Override
    public Observable<WorklogDomain> loadWorklog(@NonNull final String aTaskKey){
        return mAppDatabase.getWorklogDao().getAllWorklogsByTaskId(aTaskKey)
                .map(WorklogEntityMapper::mapWorklogEntityToWorklogDomain);
    }

    @NonNull
    @Override
    public Single<WorklogDomain> createWorklog(@NonNull String aTaskKey, @NonNull final WorklogDomain aWorklogDomain){
        WorklogOutputEntity worklogOutputEntity = WorklogEntityMapper.mapWorklogEntityFromWorklogDomain(aWorklogDomain);
        String jsonObject = WorklogEntityMapper.mapWorklogEntityToJsonObject(worklogOutputEntity);
        return
                mAppDatabase.addWorklog(aTaskKey, jsonObject)
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
        Single<Response<ResponseBody>> single = mAppDatabase.updateWorklog(aTaskKey, worklogOutputEntity.getId(), jsonObject);
        single.subscribe(aResponseBodyResponse -> {
            if(aResponseBodyResponse.isSuccessful()){
                Timber.d("Success");
            } else{
                Timber.d("Error " + aResponseBodyResponse.code());
            }
        }, Timber::e);
    }
}
