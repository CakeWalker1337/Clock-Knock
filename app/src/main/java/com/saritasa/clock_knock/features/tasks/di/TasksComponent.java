package com.saritasa.clock_knock.features.tasks.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.tasks.presentation.TasksFragment;

import dagger.Subcomponent;

@TasksScope
@Subcomponent(modules = {TasksModule.class})
public interface TasksComponent{

    void inject(TasksFragment aTasksFragment);

    @Subcomponent.Builder
    interface Builder{

        @NonNull
        Builder tasksModule(@NonNull TasksModule aModule);

        @NonNull
        TasksComponent build();
    }

}
