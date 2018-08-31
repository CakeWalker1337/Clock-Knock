package com.saritasa.clock_knock.features.login.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.base.presentation.BaseFragment;
import com.saritasa.clock_knock.features.main.presentation.NavigationListener;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * The Login fragment class
 */
public class LoginFragment extends BaseFragment implements LoginView{

    @Inject
    public LoginPresenter mLoginPresenter;
    @BindView(R.id.loginButton)
    Button mLoginButton;

    @BindView(R.id.coordinator)
    CoordinatorLayout mCoordinatorLayout;
    private NavigationListener mNavigationListener;

    public LoginFragment(){
    }

    @Override
    public void onAttach(Context aContext){
        super.onAttach(aContext);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater aLayoutInflater, ViewGroup aContainer,
                             Bundle aSavedInstanceState){

        return aLayoutInflater.inflate(R.layout.fragment_login, aContainer, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable final Bundle aSavedInstanceState){
        super.onActivityCreated(aSavedInstanceState);

        if(getActivity() != null){
            if(getActivity() instanceof NavigationListener){
                mNavigationListener = (NavigationListener) getActivity();
            }

            App.get(getActivity()).getAppComponent()
                    .loginComponentBuilder()
                    .build()
                    .inject(this);

            mLoginPresenter.attachView(this);

        }

        if(mLoginPresenter.isAccessTokenExist()){
            mLoginPresenter.completeAuthorization();
        }

        mLoginButton.setOnClickListener(aView -> mLoginPresenter.onLoginClicked());
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mNavigationListener = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroyView(){
        mLoginPresenter.detachView(this);
        super.onDestroyView();
    }

    @Override
    public void goToAuthFragment(){
        if(mNavigationListener != null){
            mNavigationListener.goToAuth();
        }
    }

    @Override
    public void showError(@NonNull final String aMessage){
        View view = getView();
        if(view != null){
            Snackbar.make(mCoordinatorLayout, aMessage, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAuthorizationComplete(){
        mNavigationListener.goToTasks();
    }
}
