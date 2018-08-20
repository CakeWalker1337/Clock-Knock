package com.saritasa.clock_knock.features.login.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView>{

    public void onLoginClicked() {
        getViewState().goToAuthFragment();
    }
}
