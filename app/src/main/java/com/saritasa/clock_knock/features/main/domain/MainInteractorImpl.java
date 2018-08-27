package com.saritasa.clock_knock.features.main.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.main.data.MainRepository;

/**
 * An implementation interactor class for executing main methods
 */
public class MainInteractorImpl extends BaseInteractorImpl<MainRepository> implements MainInteractor{

    MainRepository mMainRepository;

    /**
     * @param aMainRepository Main repository
     */
    public MainInteractorImpl(@NonNull final MainRepository aMainRepository){
        super(aMainRepository);
        mMainRepository = aMainRepository;
    }
}
