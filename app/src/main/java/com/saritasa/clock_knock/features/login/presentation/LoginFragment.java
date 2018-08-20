package com.saritasa.clock_knock.features.login.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.saritasa.clock_knock.R;

public class LoginFragment extends MvpAppCompatFragment implements LoginView{

    private NavigationCallback mNavigationCallback;

    @InjectPresenter
    public LoginPresenter mLoginPresenter;

    public LoginFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof NavigationCallback){
            mNavigationCallback = (NavigationCallback) context;
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
