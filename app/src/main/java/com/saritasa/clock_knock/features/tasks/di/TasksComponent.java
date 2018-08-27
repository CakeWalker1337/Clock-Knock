package com.saritasa.clock_knock.features.tasks.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.presentation.TasksFragment;

import dagger.Subcomponent;

/**
 * Component for tasks feature.
 */
@TasksScope
@Subcomponent(modules = {TasksModule.class})
public interface TasksComponent{

    /**
     * Injects dependencies into TasksFragment class.
     *
     * @param aTasksFragment class for injecting dependencies.
     */
    void inject(@NonNull TasksFragment aTasksFragment);

    /**
     * Builder of tasks module.
     */
    @Subcomponent.Builder
    interface Builder{

        /**
         * Creates builder of tasks module.
         * @param aModule tasks module object.
         * @return just builder.
         */
        @NonNull
        Builder tasksModule(@NonNull TasksModule aModule);

        /**
         * Builds component.
         * @return Built component.
         */
        @NonNull
        TasksComponent build();
    }

}
