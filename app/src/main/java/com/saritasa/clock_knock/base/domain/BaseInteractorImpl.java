package com.saritasa.clock_knock.base.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;

public class BaseInteractorImpl<REPOSITORY extends BaseRepository> implements BaseInteractor{

    protected REPOSITORY mRepository;

    public BaseInteractorImpl(REPOSITORY aRepository) {
        mRepository = aRepository;
    }

    @NonNull
    public String getStringResource(int aId, Object... aParams){
        return mRepository.getStringResource(aId, aParams);
    }
}
