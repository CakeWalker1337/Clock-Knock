package com.saritasa.clock_knock.features.authorization.presentation;

import com.arellomobile.mvp.MvpView;

public interface AuthPresenter<VIEW extends MvpView>{

    void attachView(VIEW aView);

    void detachView(VIEW aView);

    void onAuthAllowed(String aPage);

    void onAuthDenied();

    void getAuthPage();
}
