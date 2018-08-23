package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.data.TasksRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tasks interactor test class
 */
@RunWith(MockitoJUnitRunner.class)
public class TasksInteractorImplTest{

    @Mock
    TasksRepository mTasksRepository;

    TasksInteractor mTasksInteractor;

    /**
     * Initializes interactor object using provided repository object.
     */
    @Before
    public void init(){
        mTasksInteractor = new TasksInteractorImpl(mTasksRepository);
    }

    /**
     * Tests correct tasks loading.
     */
    @Test
    public void loadTasks_resultCorrect(){
        Observable<TasksDomain> tasksDomainObservable = Observable.create(emitter -> {
            emitter.onNext(mock(TasksDomain.class));
        });
        when(mTasksRepository.loadTasks()).thenReturn(tasksDomainObservable);
        mTasksInteractor.loadTasks()
                .test()
                .assertSubscribed()
                .assertNoErrors();

        verify(mTasksRepository).loadTasks();

    }
}