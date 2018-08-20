package com.saritasa.clock_knock.features.tasks.di;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.tasks.data.TasksRepository;
import com.saritasa.clock_knock.features.tasks.data.TasksRepositoryImpl;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractor;
import com.saritasa.clock_knock.features.tasks.domain.TasksInteractorImpl;
import com.saritasa.clock_knock.features.tasks.presentation.TasksPresenter;
import com.saritasa.clock_knock.features.tasks.presentation.TasksPresenterImpl;

import dagger.Module;
import dagger.Provides;

@TasksScope
@Module
public class TasksModule{

    @Provides
    @TasksScope
    TasksInteractor provideTasksInteractor(TasksRepository aTasksRepository){
        return new TasksInteractorImpl(aTasksRepository);
    }

    @Provides
    @TasksScope
    TasksRepository provideTasksRepository(ResourceManager aResourceManager, RestApi aRestApi){
        return new TasksRepositoryImpl(aResourceManager, aRestApi);
    }

    @Provides
    @TasksScope
    TasksPresenter provideTasksPresenter(TasksInteractor aTasksInteractor){
        return new TasksPresenterImpl(aTasksInteractor);
    }

}
