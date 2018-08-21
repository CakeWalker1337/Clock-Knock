package com.saritasa.clock_knock.features.authorization.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.authorization.presentation.AuthFragment;
import com.saritasa.clock_knock.features.main.presentation.MainActivity;

import dagger.Subcomponent;

@AuthScope
@Subcomponent(modules = AuthModule.class)
public interface AuthComponent{

    void inject(@NonNull AuthFragment aActivity);

    /**
     * Authorization component builder
     */
    @Subcomponent.Builder
    interface Builder{

        @NonNull
        Builder authModule(@NonNull AuthModule aModule);

        @NonNull
        AuthComponent build();
    }

}
