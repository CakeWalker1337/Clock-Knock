package com.saritasa.clock_knock.features.main.presentation;

import com.arellomobile.mvp.MvpView;

public interface MainPresenter<VIEW extends MvpView>{

    void attachView(VIEW aView);

    void detachView(VIEW aView);

    boolean checkAccessToken();
}
