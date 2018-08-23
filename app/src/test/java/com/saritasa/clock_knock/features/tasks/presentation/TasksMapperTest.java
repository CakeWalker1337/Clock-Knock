package com.saritasa.clock_knock.features.tasks.presentation;

import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import org.junit.Test;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;

/**
 * Tasks mapper test class.
 */
public class TasksMapperTest{

    /**
     * Tests mapping and sorting objects.
     */
    @Test
    public void mapDomainObjectsToPresentationObjects_isTasksAdapterItemsSorted(){
        TasksMapper.mapDomainObjectsToPresentationObjects(Observable.create(emitter -> {
            emitter.onNext(createTasksDomain("name1", 2));
            emitter.onNext(createTasksDomain("name2", 3));
            emitter.onNext(createTasksDomain("name3", 1));
            emitter.onComplete();
        }))
                .subscribe(aTasksAdapterItems -> {
                    String[] mTestObjectNames = {"name3", "name1", "name2"};
                    assertEquals(mTestObjectNames.length, aTasksAdapterItems.size());
                    for(int elementIndex = 0; elementIndex < aTasksAdapterItems.size(); elementIndex++){
                        assertEquals("Objects not equal: " + mTestObjectNames[elementIndex] + " != " + aTasksAdapterItems.get(elementIndex).getName() + "\n",
                                     mTestObjectNames[elementIndex],
                                     aTasksAdapterItems.get(elementIndex).getName());
                    }
                });
    }

    /**
     * Tests mapping domain object to presentation object. Results before and after launching will be equal.
     */
    @Test
    public void mapDomainObjectToPresentationObject_isObjectsEquals(){
        TasksDomain domainObject = createTasksDomain("name1", 1);
        TasksAdapterItem mappedObject = TasksMapper.mapDomainObjectToPresentationObject(domainObject);

        assertEquals(domainObject.getName(), mappedObject.getName());
        assertEquals(domainObject.getStatus(), mappedObject.getStatus());
        assertEquals(domainObject.getId(), mappedObject.getId());
        assertEquals(domainObject.getPriorityIconUrl(), mappedObject.getPriorityIconUrl());
        assertEquals(domainObject.getProjectAvatarUrl(), mappedObject.getProjectAvatarUrl());
        assertEquals(domainObject.getSummary(), mappedObject.getSummary());

    }

    /**
     * Creates tasks domain object.
     *
     * @param aName name of task.
     * @param aPriorityId priority of task. System will sort tasks by this param.
     * @return Domain object.
     */
    private TasksDomain createTasksDomain(String aName, int aPriorityId){
        TasksDomain tasksDomain = new TasksDomain();
        tasksDomain.setPriorityId(aPriorityId);
        tasksDomain.setName(aName);
        tasksDomain.setStatus("IN PROGRESS");
        tasksDomain.setId("1");
        tasksDomain.setPriorityIconUrl("https://nothing.com/");
        tasksDomain.setProjectAvatarUrl("https://nothing.com/");
        tasksDomain.setSummary("Test tasks domain");
        return tasksDomain;
    }
}