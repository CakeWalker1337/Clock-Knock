package com.saritasa.clock_knock.features.login.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.login.domain.LoginInteractor;

import io.reactivex.disposables.Disposable;

/**
 * A class which implements methods from LoginPresenter interface
 */
@InjectViewState
public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter<LoginView>{

    private LoginInteractor mLoginInteractor;

    /**
     * @param aLoginInteractor Login interactor
     */
    public LoginPresenterImpl(@NonNull LoginInteractor aLoginInteractor){
        mLoginInteractor = aLoginInteractor;
    }

    @Override
    public void attachView(@NonNull LoginView aAuthView){
        super.attachView(aAuthView);
    }

    @Override
    public void detachView(@NonNull LoginView aAuthView){
        super.detachView(aAuthView);
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
