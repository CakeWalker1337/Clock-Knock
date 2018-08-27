package com.saritasa.clock_knock.features.login.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.main.presentation.NavigationListener;

import javax.inject.Inject;

/**
 * The Login fragment class
 */
public class LoginFragment extends MvpAppCompatFragment implements LoginView{

    private NavigationListener mNavigationListener;

    @Inject
    public LoginPresenter mLoginPresenter;

    public LoginFragment(){
    }

    @Override
    public void onAttach(Context aContext){
        super.onAttach(aContext);

        // Initialize the Navigation listener
        if(aContext instanceof NavigationListener){
            mNavigationListener = (NavigationListener) aContext;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        App.get(getView().getContext()).getAppComponent()
                .loginComponentBuilder()
                .build()
                .inject(this);

        mLoginPresenter.attachView(this);

        if (mLoginPresenter.isAccessTokenExist()) {
            mLoginPresenter.completeAuthorization();
        }

        Button button = view.findViewById(R.id.loginButton);
        button.setOnClickListener(aView -> mLoginPresenter.onLoginClicked());
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mLoginPresenter.detachView(this);
        mNavigationListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView(this);
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
            Snackbar.make(view, aMessage, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAuthorizationComplete(){
        mNavigationListener.goToTasks();
    }
}
