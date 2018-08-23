package com.saritasa.clock_knock.features.main.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.main.domain.MainInteractor;

import io.reactivex.disposables.Disposable;

/**
 * A class which implements methods from MainPresenter interface
 */
@InjectViewState
public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter<MainView>{

    private MainInteractor mMainInteractor;

    /**
     * @param aMainInteractor Main interactor
     */
    public MainPresenterImpl(MainInteractor aMainInteractor) {
        mMainInteractor = aMainInteractor;
    }
}
