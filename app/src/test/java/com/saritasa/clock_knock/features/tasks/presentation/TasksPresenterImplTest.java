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

import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
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
    public void loadTasks_methodsCallCorrectly(){

        List<TasksAdapterItem> testList = mock(List.class);

        testList.add(getTestTaskAdapterItem());
        Single<List<TasksAdapterItem>> testSingleList = Single.create(emitter -> {
            emitter.onSuccess(testList);
        });

        when(mInteractor.loadTasks()).thenReturn(testSingleList);
        mTasksPresenter.loadTasks();
        verify(mTasksView).updateView(testList);
    }

    /**
     * Creates tasks presentation object (TasksAdapterItem) with hardcoded params.
     *
     * @return new adapter item.
     */
    private TasksAdapterItem getTestTaskAdapterItem(){
        TasksAdapterItem tasksDomain = new TasksAdapterItem();
        tasksDomain.setName("Name");
        tasksDomain.setStatus("IN PROGRESS");
        tasksDomain.setId("1");
        tasksDomain.setPriorityIconUrl("https://nothing.com/");
        tasksDomain.setProjectAvatarUrl("https://nothing.com/");
        tasksDomain.setSummary("Test tasks domain");
        return tasksDomain;
    }

}