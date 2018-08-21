package com.saritasa.clock_knock.features.tasks.data;


import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.tasks.domain.TasksDomain;

import io.reactivex.Observable;

public interface TasksRepository extends BaseRepository{

    Observable<TasksDomain> loadTasks();

}
