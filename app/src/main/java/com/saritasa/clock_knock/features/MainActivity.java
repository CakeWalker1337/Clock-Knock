package com.saritasa.clock_knock.features;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.authorization.presentation.AuthFragment;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.NavigationCallback, AuthFragment.NavigationCallback{

    @Override
    protected void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginFragment fragment = new LoginFragment();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        fragmentManager.beginTransaction().show(fragment).commit();
    }

    @Override
    public void goToAuth(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AuthFragment()).commit();
    }

    @Override
    public void goToLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).commit();
    }
}
