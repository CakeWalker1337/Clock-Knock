package com.saritasa.clock_knock.features.worklog.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.worklog.data.WorklogRepository;
import com.saritasa.clock_knock.features.worklog.data.WorklogRepositoryImpl;
import com.saritasa.clock_knock.features.worklog.domain.WorklogInteractor;
import com.saritasa.clock_knock.features.worklog.domain.WorklogInteractorImpl;
import com.saritasa.clock_knock.features.worklog.presentation.WorklogPresenter;
import com.saritasa.clock_knock.features.worklog.presentation.WorklogPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Worklog module DI class
 */
@WorklogScope
@Module
public class WorklogModule{

    /**
     * Provides interactor of Worklog module through DI. Singleton.
     *
     * @param aWorklogRepository Provided repository object using in interactor.
     * @return Interactor of Worklog module.
     */
    @NonNull
    @Provides
    @WorklogScope
    WorklogInteractor provideWorklogInteractor(@NonNull WorklogRepository aWorklogRepository, SessionRepository aSessionRepository){
        return new WorklogInteractorImpl(aWorklogRepository, aSessionRepository);
    }

    /**
     * Provides repository of Worklog module through DI. Singleton.
     *
     * @param aResourceManager Provided resource manager object using in repository.
     * @param aRestApi Provided API object using in repository.
     * @return Repository of Worklog module.
     */
    @NonNull
    @Provides
    @WorklogScope
    WorklogRepository provideWorklogRepository(@NonNull ResourceManager aResourceManager, @NonNull RestApi aRestApi, SessionRepository aSessionRepository){
        return new WorklogRepositoryImpl(aResourceManager, aRestApi, aSessionRepository);
    }

    /**
     * Provides presenter of Worklog module through DI. Singleton.
     *
     * @param aWorklogInteractor Provided interactor object for using in presenter.
     * @return Presenter of Worklog module.
     */
    @NonNull
    @Provides
    @WorklogScope
    WorklogPresenter provideWorklogPresenter(@NonNull WorklogInteractor aWorklogInteractor){
        return new WorklogPresenterImpl(aWorklogInteractor);
    }

}
