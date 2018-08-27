package com.saritasa.clock_knock.features.tasks.presentation;

import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;
import com.saritasa.clock_knock.utils.RxSchedulerRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tasks Presenter test class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TasksPresenterImplTest{

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Mock
    TasksInteractor mInteractor;

    @Mock
    TasksView mTasksView;

    TasksPresenter mTasksPresenter;

    /**
     * Creates presenter by provided interactor object and attaches provided view.
     */
    @Before
    public void setUp(){
        mTasksPresenter = new TasksPresenterImpl(mInteractor);
        mTasksPresenter.attachView(mTasksView);
    }

    /**
     * Detaches view.
     */
    @After
    public void destroy(){
        mTasksPresenter.detachView(mTasksView);
    }

    /**
     * Test loading tasks. Verifies called methods.
     */
    @Test
    public void onRequest_methodsCallCorrectly(){
        when(mInteractor.loadTasks()).thenReturn(Observable.empty());
        mTasksPresenter.onRequest();
        verify(mTasksView).updateTaskList(anyList());
        verify(mTasksView).hideLoadingProgress();
        verify(mTasksView).showLoadingProgress();
    }


}