package com.saritasa.clock_knock.features.tasks.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.local.AppDatabase;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
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
    @NonNull
    @Provides
    @TasksScope
    TasksInteractor provideTasksInteractor(@NonNull TasksRepository aTasksRepository){
        return new TasksInteractorImpl(aTasksRepository);
    }

    /**
     * Provides repository of tasks module through DI. Singleton.
     * @param aResourceManager Provided resource manager object using in repository.
     * @param aAppDatabase Provided API object using in repository.
     * @param aSessionRepository Provided SessionRepository object using in this repository.
     * @return Repository of tasks module.
     */
    @NonNull
    @Provides
    @TasksScope
    TasksRepository provideTasksRepository(@NonNull ResourceManager aResourceManager, AppDatabase aAppDatabase, SessionRepository aSessionRepository){
        return new TasksRepositoryImpl(aResourceManager, aAppDatabase, aSessionRepository);
    }

    /**
     * Provides presenter of tasks module through DI. Singleton.
     * @param aTasksInteractor Provided interactor object for using in presenter.
     * @return Presenter of tasks module.
     */
    @NonNull
    @Provides
    @TasksScope
    TasksPresenter provideTasksPresenter(@NonNull TasksInteractor aTasksInteractor){
        return new TasksPresenterImpl(aTasksInteractor);
    }

}
