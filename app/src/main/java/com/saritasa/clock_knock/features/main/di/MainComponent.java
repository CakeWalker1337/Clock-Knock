package com.saritasa.clock_knock.features.main.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.authorization.di.AuthComponent;
import com.saritasa.clock_knock.features.main.presentation.MainActivity;

import dagger.Subcomponent;

@MainScope
@Subcomponent
public interface MainComponent{

    void inject(@NonNull MainActivity aActivity);

    /**
     * Authorization component builder
     */
    @Subcomponent.Builder
    interface Builder{

        @NonNull
        MainComponent.Builder mainModule(@NonNull MainModule aModule);

        @NonNull
        MainComponent build();
    }
}
