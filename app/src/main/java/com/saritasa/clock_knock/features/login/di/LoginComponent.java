package com.saritasa.clock_knock.features.login.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.authorization.di.AuthModule;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;

import dagger.Subcomponent;

@LoginScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent{

    void inject(@NonNull LoginFragment aLoginFragment);

    /**
     * Authorization component builder
     */
    @Subcomponent.Builder
    interface Builder{

        @NonNull
        Builder loginModule(@NonNull LoginModule aLoginModule);

        @NonNull
        LoginComponent build();
    }

}