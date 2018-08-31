package com.saritasa.clock_knock.features.worklog.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.worklog.presentation.WorklogFragment;

import dagger.Subcomponent;

/**
 * Worklog DI component for injecting objects into the worklog fragments.
 */
@WorklogScope
@Subcomponent(modules = {WorklogModule.class})
public interface WorklogComponent{

    /**
     * Injects objects into the worklog fragment.
     *
     * @param aWorklogFragment frogment for injecting.
     */
    void inject(WorklogFragment aWorklogFragment);

    /**
     * Builder interface for the worklog module.
     */
    @Subcomponent.Builder
    interface Builder{

        /**
         * Provides builder of the worklog module.
         *
         * @param aModule worklog module object.
         * @return Builder object.
         */
        @NonNull
        Builder worklogModule(@NonNull WorklogModule aModule);

        /**
         * Builds worklog component.
         *
         * @return Worklog component object.
         */
        @NonNull
        WorklogComponent build();

    }
}
