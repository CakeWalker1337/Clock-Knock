package com.saritasa.clock_knock.features.main.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.auth.presentation.AuthFragment;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * The Main activity class
 */
public class MainActivity extends MvpAppCompatActivity implements MainView, NavigationListener{

    @Inject
    public MainPresenter mMainPresenter;

    private AuthFragment mAuthFragment;
    private LoginFragment mLoginFragment;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        App.get(this).getAppComponent()
                .mainComponentBuilder()
                .build()
                .inject(this);

        mMainPresenter.attachView(this);

        goToLogin();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroy(){
        mMainPresenter.detachView(this);
        super.onDestroy();
    }

    @Override
    public void onAuthenticationComplete(){
        goToLogin();
    }

    @Override
    public void goToLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).commit();
    }

    @Override
    public void goToAuth(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AuthFragment()).commit();
    }

    @Override
    public void goToTasks(){
        // TODO: Show task fragment
    }
}
