package com.saritasa.clock_knock.features.main.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.GlobalRepository;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.main.data.MainRepository;
import com.saritasa.clock_knock.features.main.data.MainRepositoryImpl;
import com.saritasa.clock_knock.features.main.domain.MainInteractor;
import com.saritasa.clock_knock.features.main.domain.MainInteractorImpl;
import com.saritasa.clock_knock.features.main.presentation.MainPresenter;
import com.saritasa.clock_knock.features.main.presentation.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * A class for providing the necessary objects from main module
 */
@MainScope
@Module
public class MainModule{

    /**
     * Provides the Main presenter
     *
     * @param aMainInteractor Main interactor object
     * @return Main presenter object
     */
    @NonNull
    @MainScope
    @Provides
    public MainPresenter providesPresenter(MainInteractor aMainInteractor){
        return new MainPresenterImpl(aMainInteractor);
    }

    /**
     * Provides the Main interactor
     *
     * @param aMainRepository Main repository object
     * @return Main interactor object
     */
    @NonNull
    @MainScope
    @Provides
    public MainInteractor providesInteractor(MainRepository aMainRepository) {
        return new MainInteractorImpl(aMainRepository);
    }

    /**
     * Provides the Main repository
     *
     * @param aResourceManager Resource manager object
     * @param aGlobalRepository Global repository object
     * @return Main repository object
     */
    @NonNull
    @MainScope
    @Provides
    public MainRepository providesRepository(ResourceManager aResourceManager, GlobalRepository aGlobalRepository) {
        return new MainRepositoryImpl(aResourceManager, aGlobalRepository);
    }
}
