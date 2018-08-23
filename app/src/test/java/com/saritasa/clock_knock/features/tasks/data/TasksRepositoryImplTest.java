package com.saritasa.clock_knock.features.tasks.data;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Observable;

/**
 * Tasks Repository test class
 */
public class TasksRepositoryImplTest{

    @Mock
    ResourceManager mResourceManager;
    @Mock
    RestApi mRestApi;

    TasksRepository mTasksRepository;

    /**
     * Initializes repository object by provided resource manager and api object.
     */
    @Before
    public void init(){
        mTasksRepository = new TasksRepositoryImpl(mResourceManager, mRestApi);
    }

    /**
     * Tests tasks loading.
     */
    @Test
    public void loadTasks_resultCorrect(){
        Observable<TasksDomain> result = mTasksRepository.loadTasks();
        result.test()
                .assertComplete()
                .assertNoErrors();
    }
}