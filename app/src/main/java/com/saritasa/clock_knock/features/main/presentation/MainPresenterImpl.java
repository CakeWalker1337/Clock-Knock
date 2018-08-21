package com.saritasa.clock_knock.features.main.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.main.domain.MainInteractor;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter<MainView>{

    private MainInteractor mMainInteractor;

    public MainPresenterImpl(MainInteractor aMainInteractor) {
        mMainInteractor = aMainInteractor;
    }

    @Override
    public boolean checkAccessToken(){
        return mMainInteractor.checkAccessToken();
    }

}
