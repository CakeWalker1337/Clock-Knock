package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.local.AppDatabase;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
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
    public ArrayList<WorklogDomain> loadWorklog(long aTaskId){
        List<WorklogEntity> worklogEntities = mAppDatabase.getWorklogDao().getAllWorklogsByTaskId(aTaskId);

        ArrayList<WorklogDomain> worklogDomains = new ArrayList<>();
        for(WorklogEntity worklogEntity : worklogEntities){
            worklogDomains.add(WorklogEntityMapper.mapWorklogEntityToWorklogDomain(worklogEntity));
        }
        worklogEntities.clear();
        return worklogDomains;
    }

    @Override
    public long createWorklog(long aTaskId, @NonNull final WorklogDomain aWorklogDomain){
        WorklogEntity worklogEntity = WorklogEntityMapper.mapWorklogEntityFromWorklogDomain(aWorklogDomain);
        worklogEntity.setTaskId(aTaskId);
        return mAppDatabase.getWorklogDao().createWorklog(worklogEntity);
    }

    @Override
    public int updateWorklog(long aTaskId, @NonNull final WorklogDomain aWorklogDomain){
        WorklogEntity worklogEntity = WorklogEntityMapper.mapWorklogEntityFromWorklogDomain(aWorklogDomain);
        worklogEntity.setTaskId(aTaskId);
        return mAppDatabase.getWorklogDao().updateWorklog(worklogEntity);
    }

    @Override
    public int deleteWorklog(long aTaskId, @NonNull final WorklogDomain aWorklogDomain){
        WorklogEntity worklogEntity = WorklogEntityMapper.mapWorklogEntityFromWorklogDomain(aWorklogDomain);
        worklogEntity.setTaskId(aTaskId);
        return mAppDatabase.getWorklogDao().deleteWorklog(worklogEntity);
    }
}
