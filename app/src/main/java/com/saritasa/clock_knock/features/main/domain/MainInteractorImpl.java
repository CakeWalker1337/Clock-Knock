package com.saritasa.clock_knock.features.main.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.main.data.MainRepository;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.util.Constants;

/**
 * An implementation interactor class for executing main methods
 */
public class MainInteractorImpl extends BaseInteractorImpl<MainRepository> implements MainInteractor{

    MainRepository mMainRepository;
    SessionRepository mSessionRepository;

    /**
     * @param aMainRepository Main repository
     */
    public MainInteractorImpl(@NonNull final MainRepository aMainRepository, @NonNull final SessionRepository aSessionRepository){
        super(aMainRepository);
        mMainRepository = aMainRepository;
        mSessionRepository = aSessionRepository;
    }

    @Override
    public boolean isTimerActive(){
        return mSessionRepository.getStartTimestamp() != Constants.UNDEFINED_VALUE;
    }

    @Override
    public long getStartTimestamp(){
        return mSessionRepository.getStartTimestamp();
    }

    @Nullable
    @Override
    public String getTaskId(){
        return mSessionRepository.getTaskId();
    }
}
