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

/**
 * DI Tasks module class.
 */
@TasksScope
@Module
public class TasksModule{

    /**
     * Provides interactor of tasks module through DI. Singleton.
     *
     * @param aTasksRepository Provided repository object using in interactor.
     * @return Interactor of tasks module.
     */
    @Provides
    @TasksScope
    TasksInteractor provideTasksInteractor(TasksRepository aTasksRepository){
        return new TasksInteractorImpl(aTasksRepository);
    }

    /**
     * Provides repository of tasks module through DI. Singleton.
     * @param aResourceManager Provided resource manager object using in repository.
     * @param aRestApi Provided API object using in repository.
     * @return Repository of tasks module.
     */
    @Provides
    @TasksScope
    TasksRepository provideTasksRepository(ResourceManager aResourceManager, RestApi aRestApi){
        return new TasksRepositoryImpl(aResourceManager, aRestApi);
    }

    /**
     * Provides presenter of tasks module through DI. Singleton.
     * @param aTasksInteractor Provided interactor object for using in presenter.
     * @return Presenter of tasks module.
     */
    @Provides
    @TasksScope
    TasksPresenter provideTasksPresenter(TasksInteractor aTasksInteractor){
        return new TasksPresenterImpl(aTasksInteractor);
    }

}
