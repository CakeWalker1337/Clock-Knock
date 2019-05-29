package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.local.AppDatabase;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Repository object of tasks feature.
 */
public class TasksRepositoryImpl extends BaseRepositoryImpl implements TasksRepository{

    private SessionRepository mSessionRepository;
    private AppDatabase mAppDatabase;

    /**
     * @param aResourceManager - resource manager object
     * @param aAppDatabase - object of rest api
     */
    public TasksRepositoryImpl(@NonNull ResourceManager aResourceManager, @NonNull AppDatabase aAppDatabase, SessionRepository aSessionRepository){
        super(aResourceManager);
        mAppDatabase = aAppDatabase;
        mSessionRepository = aSessionRepository;
    }

    @NonNull
    @Override
    public ArrayList<TasksDomain> loadTasks(){
        List<TaskEntity> taskEntities = mAppDatabase.getTaskDao().getAllTasks();

        ArrayList<TasksDomain> tasksDomains = new ArrayList<>();
        for(TaskEntity taskEntity : taskEntities){
            tasksDomains.add(TasksEntityMapper.mapEntityObjectToDomainObject(taskEntity));
        }
        taskEntities.clear();

        return tasksDomains;
    }

    @Override
    public long createTask(TasksDomain aTasksDomain){
        return mAppDatabase.getTaskDao().addTask(TasksEntityMapper.mapDomainObjectToEntityObject(aTasksDomain));
    }

    @Override
    public int updateTask(TasksDomain aTasksDomain){
        return mAppDatabase.getTaskDao().updateTask(TasksEntityMapper.mapDomainObjectToEntityObject(aTasksDomain));
    }

    @Override
    public int deleteTask(TasksDomain aTasksDomain){
        return mAppDatabase.getTaskDao().deleteTask(TasksEntityMapper.mapDomainObjectToEntityObject(aTasksDomain));
    }
}
