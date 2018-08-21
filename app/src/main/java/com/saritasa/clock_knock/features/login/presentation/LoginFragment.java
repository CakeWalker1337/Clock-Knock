package com.saritasa.clock_knock.features.login.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;

import javax.inject.Inject;

public class LoginFragment extends MvpAppCompatFragment implements LoginView{

    private NavigationCallback mNavigationCallback;

    @Inject
    public LoginPresenter<LoginView> mLoginPresenter;

    public LoginFragment(){

    }

    @Override
    public void onAttach(Context aContext){
        super.onAttach(aContext);

        App.get(aContext).getAppComponent()
                .loginComponentBuilder()
                .build()
                .inject(this);

        mLoginPresenter.attachView(this);

        if(aContext instanceof NavigationCallback){
            mNavigationCallback = (NavigationCallback) aContext;
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
        Button button = view.findViewById(R.id.loginButton);
        button.setOnClickListener(aView -> {
            mLoginPresenter.onLoginClicked();
        });
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mNavigationCallback = null;
    }

    @Override
    public void goToAuthFragment(){
        if(mNavigationCallback != null){
            mNavigationCallback.goToAuth();
        }
    }

    public interface NavigationCallback{

        void goToAuth();
    }
}
