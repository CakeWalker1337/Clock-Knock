package com.saritasa.clock_knock.features.authorization.presentation;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.authorization.domain.AuthInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * A class which implements methods from AuthPresenter interface
 */
@InjectViewState
public class AuthPresenterImpl extends BasePresenter<AuthView> implements AuthPresenter<AuthView>{

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
