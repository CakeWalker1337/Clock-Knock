package com.saritasa.clock_knock.features.login.presentation;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.login.domain.LoginInteractor;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter<LoginView>{

    private LoginInteractor mLoginInteractor;

    public LoginPresenterImpl(LoginInteractor aLoginInteractor){
        mLoginInteractor = aLoginInteractor;
    }

    @Override
    public void onLoginClicked(){
        getViewState().goToAuthFragment();
    }

    @Override
    public void completeAuthorization(){
        Disposable disposable = mLoginInteractor.getUsername()
                .subscribe(aUsername -> {
                               mLoginInteractor.saveUsername(aUsername.getUsername());
                               Log.w("Username", "Username: " + aUsername.getUsername());
                               getViewState().onAuthorizationComplete();
                           },
                           aThrowable -> getViewState().showError(aThrowable.getMessage()));

        unsubscribeOnDestroy(disposable);
    }

    @Override
    public boolean isAccessTokenExist(){
        return mLoginInteractor.isAccessTokenExist();
    }
}
