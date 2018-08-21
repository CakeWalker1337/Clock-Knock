package com.saritasa.clock_knock.features.main.di;

import android.support.annotation.NonNull;

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

@MainScope
@Module
public class MainModule{

    @NonNull
    @MainScope
    @Provides
    public MainPresenter providesPresenter(MainInteractor aMainInteractor){
        return new MainPresenterImpl(aMainInteractor);
    }

    @NonNull
    @MainScope
    @Provides
    public MainInteractor providesInteractor(MainRepository aMainRepository) {
        return new MainInteractorImpl(aMainRepository);
    }

    @NonNull
    @MainScope
    @Provides
    public MainRepository providesRepository(ResourceManager aResourceManager, PreferenceManager aPreferenceManager) {
        return new MainRepositoryImpl(aResourceManager, aPreferenceManager);
    }
}
