package com.saritasa.clock_knock.features.login.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.authorization.di.AuthModule;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;

import dagger.Subcomponent;

/**
 * An interface for Login subcomponent
 */
@LoginScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent{

    /**
     * Injects this component data to LoginFragment
     *
     * @param aLoginFragment Login fragment object
     */
    void inject(@NonNull LoginFragment aLoginFragment);

    /**
     * Login component builder
     */
    @Subcomponent.Builder
    interface Builder{

        /**
         * Gets the login module builder
         *
         * @param aLoginModule Login module object
         * @return Login module builder
         */
        @NonNull
        Builder loginModule(@NonNull LoginModule aLoginModule);

        /**
         * Builds the login component
         *
         * @return Login component interface
         */
        @NonNull
        LoginComponent build();
    }

}