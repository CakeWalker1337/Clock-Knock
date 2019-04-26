package com.saritasa.clock_knock.features.main.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.main.presentation.MainActivity;

import dagger.Subcomponent;

/**
 * An interface for Main subcomponent
 */
@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent{

    /**
     * Injects this component data to MainActivity
     *
     * @param aMainActivity Main activity object
     */
    void inject(@NonNull MainActivity aMainActivity);

    /**
     * Main component builder
     */
    @Subcomponent.Builder
    interface Builder{

        /**
         * Gets the main module builder
         *
         * @param aMainModule Main module object
         * @return Main module builder
         */
        @NonNull
        MainComponent.Builder mainModule(@NonNull MainModule aMainModule);

        /**
         * Builds the main component
         *
         * @return Main component interface
         */
        @NonNull
        MainComponent build();
    }
}
