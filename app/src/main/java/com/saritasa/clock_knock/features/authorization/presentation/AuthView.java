package com.saritasa.clock_knock.features.authorization.presentation;

import com.arellomobile.mvp.MvpView;
import com.saritasa.clock_knock.base.presentation.BaseView;

public interface AuthView extends BaseView{

    void loadPageByUrl(String url);

    void goToLogin();

    void completeAuthentication();

    void showError(String aMessage);
}
