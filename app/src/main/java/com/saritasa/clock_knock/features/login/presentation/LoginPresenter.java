package com.saritasa.clock_knock.features.login.presentation;

import com.arellomobile.mvp.MvpView;

public interface LoginPresenter<VIEW extends MvpView>{

    void attachView(VIEW aView);

    void detachView(VIEW aView);

    void onLoginClicked();
}
