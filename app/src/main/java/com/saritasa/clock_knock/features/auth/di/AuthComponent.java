package com.saritasa.clock_knock.features.auth.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.auth.presentation.AuthFragment;
import com.saritasa.clock_knock.features.main.presentation.MainActivity;

import dagger.Subcomponent;

/**
 * An interface for Auth subcomponent
 */
@AuthScope
@Subcomponent(modules = AuthModule.class)
public interface AuthComponent{

    /**
     * Injects this component data to AuthFragment
     *
     * @param aAuthFragment Auth fragment object
     */
    void inject(@NonNull AuthFragment aAuthFragment);

    /**
     * Authorization component builder
     */
    @Subcomponent.Builder
    interface Builder{

        /**
         * Gets the auth module builder
         *
         * @param aModule Auth module object
         * @return Auth module builder
         */
        @NonNull
        Builder authModule(@NonNull AuthModule aModule);

        /**
         * Builds the auth component
         *
         * @return Auth component interface
         */
        @NonNull
        AuthComponent build();
    }

}
