package com.saritasa.clock_knock.features.main.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.authorization.presentation.AuthFragment;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;

import javax.inject.Inject;

public class MainActivity extends MvpAppCompatActivity implements MainView, LoginFragment.NavigationCallback, AuthFragment.NavigationCallback{

    @Inject
    MainPresenter<MainActivity> mMainPresenter;

    @Override
    protected void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        App.get(this).getAppComponent()
                .mainComponentBuilder()
                .build()
                .inject(this);

        mMainPresenter.attachView(this);

        if (!mMainPresenter.checkAccessToken()){
            FragmentManager fragmentManager = getSupportFragmentManager();
            LoginFragment fragment = new LoginFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
            fragmentManager.beginTransaction().show(fragment).commit();
        }
        else {
            onAuthenticationComplete();
        }
    }

    @Override
    public void goToAuth(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AuthFragment()).commit();
    }

    @Override
    public void onAuthenticationComplete(){
        //TODO: Add getting username from login fragment
    }

    @Override
    public void goToLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).commit();
    }
}
