package com.saritasa.clock_knock.features.auth.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.auth.domain.AuthInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * A class which implements methods from AuthPresenter interface
 */
@InjectViewState
public class AuthPresenterImpl extends BasePresenterImpl<AuthView> implements AuthPresenter<AuthView>{

    private AuthInteractor mAuthInteractor;

    /**
     * @param aAuthInteractor Auth interactor
     */
    public AuthPresenterImpl(@NonNull AuthInteractor aAuthInteractor){
        mAuthInteractor = aAuthInteractor;
    }

    @Override
    public void attachView(@NonNull AuthView aAuthView){
        super.attachView(aAuthView);
    }

    @Override
    public void detachView(@NonNull AuthView aAuthView){
        super.detachView(aAuthView);
    }

    @Override
    public void onAuthAllowed(@NonNull String aPage){
        Disposable disposable = mAuthInteractor.finishAuthentication(aPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aAccessToken -> {
                               mAuthInteractor.saveAccessToken(aAccessToken);
                               getViewState().completeAuthentication();
                           },
                           aThrowable -> getViewState().showError(aThrowable.getMessage()));

        unsubscribeOnDestroy(disposable);
    }

    @Override
    public void onAuthDenied(){
        getViewState().goToLogin();
    }

    @Override
    public void getAuthPage(){
        Disposable disposable = mAuthInteractor.getAuthPage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aUrl -> getViewState().loadPageByUrl(aUrl),
                           aThrowable -> getViewState().showError(aThrowable.getMessage()));

        unsubscribeOnDestroy(disposable);
    }

}
