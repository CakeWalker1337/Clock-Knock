package com.saritasa.clock_knock.features.login.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.login.domain.LoginInteractor;

@InjectViewState
public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter<LoginView>{

    private LoginInteractor mLoginInteractor;

    public LoginPresenterImpl(LoginInteractor aLoginInteractor) {
        mLoginInteractor = aLoginInteractor;
    }

    @Override
    public void onLoginClicked(){
        getViewState().goToAuthFragment();
    }
}
