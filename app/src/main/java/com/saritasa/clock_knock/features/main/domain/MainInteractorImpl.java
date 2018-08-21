package com.saritasa.clock_knock.features.main.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.main.data.MainRepository;

public class MainInteractorImpl extends BaseInteractorImpl<MainRepository> implements MainInteractor{

    MainRepository mMainRepository;

    public MainInteractorImpl(final MainRepository aMainRepository){
        super(aMainRepository);
        mMainRepository = aMainRepository;
    }

    @Override
    public boolean checkAccessToken(){
        return mMainRepository.checkAccessToken();
    }
}
