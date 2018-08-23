package com.saritasa.clock_knock.features.login.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenter;
import com.saritasa.clock_knock.features.authorization.presentation.AuthView;
import com.saritasa.clock_knock.features.login.domain.LoginInteractor;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * A class which implements methods from LoginPresenter interface
 */
@InjectViewState
public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter<LoginView>{

    private LoginInteractor mLoginInteractor;

    /**
     * @param aLoginInteractor Login interactor
     */
    public LoginPresenterImpl(LoginInteractor aLoginInteractor){
        mLoginInteractor = aLoginInteractor;
    }

    @Override
    public void attachView(LoginView aAuthView){
        super.attachView(aAuthView);
    }

    @Override
    public void detachView(LoginView aAuthView){
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
                               Timber.d("Username: " + aUsername.getUsername());
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
