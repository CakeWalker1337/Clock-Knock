package com.saritasa.clock_knock.features.main.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.main.domain.MainInteractor;

/**
 * A class which implements methods from MainPresenter interface
 */
@InjectViewState
public class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter<MainView>{

    private MainInteractor mMainInteractor;

    /**
     * @param aMainInteractor Main interactor
     */
    public MainPresenterImpl(@NonNull MainInteractor aMainInteractor) {
        mMainInteractor = aMainInteractor;
    }

    @Override
    public boolean isTimerActive(){
        return mMainInteractor.isTimerActive();
    }

    @Override
    public void onTimerActivityChecked(){
        long taskId = mMainInteractor.getTaskId();
        String taskKey = mMainInteractor.getTaskKey();
        long timestamp = mMainInteractor.getStartTimestamp();
        getViewState().startTimer(taskId, taskKey, timestamp);
    }
}
