package com.saritasa.clock_knock.features.main.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.authorization.presentation.AuthFragment;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;

import javax.inject.Inject;

public class MainActivity extends MvpAppCompatActivity implements MainView, NavigationListener{

    @Inject
    public MainPresenter mMainPresenter;

    private AuthFragment mAuthFragment;
    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        App.get(this).getAppComponent()
                .mainComponentBuilder()
                .build()
                .inject(this);

        mMainPresenter.attachView(this);

        mAuthFragment = new AuthFragment();
        mLoginFragment = new LoginFragment();

        goToLogin();

        if (mMainPresenter.checkAccessToken()){
            onAuthenticationComplete();
        }
        else {
            goToLogin();
        }
    }

    @Override
    public void goToAuth(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mAuthFragment).commit();
    }

    @Override
    public void onAuthenticationComplete(){
        goToLogin();
        mLoginFragment.completeAuthorization();
    }

    @Override
    public void goToTasks(){
        // Show task fragment
        Log.w("MainActivity", "Showing task fragment");
    }

    @Override
    public void goToLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mLoginFragment).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView(this);
    }
}
