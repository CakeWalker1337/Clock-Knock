package com.saritasa.clock_knock.features.tasks.domain;

import com.saritasa.clock_knock.features.tasks.data.TasksRepository;
import com.saritasa.clock_knock.utils.RxSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tasks mapper test class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TasksInteractorImplTest{

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Mock
    TasksRepository mRepository;

    TasksInteractor mTasksInteractor;

    /**
     * Creates interactor by provided repository object.
     */
    @Before
    public void setUp(){
        mTasksInteractor = new TasksInteractorImpl(mRepository);
    }

    /**
     * Tests load task method, is filter works correctly or not.
     */
    @Test
    public void loadTasks_isFilterWorksCorrectly(){

        Observable<TasksDomain> testObservable = Observable.just(generateTasksDomainObject("obj1", "Done", 1),
                                                                 generateTasksDomainObject("obj2", "Done", 1),
                                                                 generateTasksDomainObject("obj3", "IN PROGRESS", 1));

        when(mRepository.loadTasks()).thenReturn(testObservable);
        Observable<TasksDomain> result = mTasksInteractor.loadTasks();
        result.test()
                .assertValueCount(1);
        result.subscribe(aTasksDomain -> assertEquals("obj3", aTasksDomain.getName()));
    }

    /**
     * Tests load task method, is sorting works correctly or not.
     */
    @Test
    public void loadTasks_isSortWorksCorrectly(){

        Observable<TasksDomain> testObservable = Observable.just(generateTasksDomainObject("obj1", "IN PROGRESS", 3),
                                                                 generateTasksDomainObject("obj2", "IN PROGRESS", 1),
                                                                 generateTasksDomainObject("obj3", "IN PROGRESS", 2));

        when(mRepository.loadTasks()).thenReturn(testObservable);
        Observable<TasksDomain> result = mTasksInteractor.loadTasks();
        result.test()
                .assertValueCount(3)
                .assertNoErrors();
        result.toList().subscribe(aTasksDomains -> {
            String[] sortedObjectNames = {"obj2", "obj3", "obj1"};
            for(int i = 0; i < aTasksDomains.size(); i++){
                assertEquals(sortedObjectNames[i], aTasksDomains.get(i).getName());
            }
        });

    }

    /**
     * Generates task domain object with params.
     *
     * @param aName name of object.
     * @param aStatus status of object.
     * @param aPriorityId priority id.
     * @return
     */
    public TasksDomain generateTasksDomainObject(String aName, String aStatus, int aPriorityId){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setStatus(aStatus);
        tasksDomain.setName(aName);
        tasksDomain.setPriorityId(aPriorityId);
        return tasksDomain;
    }

}
