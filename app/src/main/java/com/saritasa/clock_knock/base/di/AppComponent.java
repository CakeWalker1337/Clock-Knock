package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.features.tasks.di.TasksComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent{

    void inject(App aApp);

    @NonNull
    TasksComponent.Builder tasksComponentBuilder();

}
