package com.saritasa.clock_knock.features.authorization.presentation;

import com.arellomobile.mvp.MvpView;

public interface AuthView extends MvpView {

    void loadPageByUrl(String url);

    void goToLogin();
}
