package com.saritasa.clock_knock.base.di;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.features.tasks.di.TasksComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main component of application. Includes api module and app module.
 */
@Singleton
@Component(modules = {ApiModule.class, AppModule.class})
public interface AppComponent{

    /**
     * Injects component into App class.
     *
     * @param aApp class to inject.
     */
    void inject(App aApp);

    /**
     * Builder of tasks subcomponent module.
     * @return tasks component builder.
     */
    @NonNull
    TasksComponent.Builder tasksComponentBuilder();

}
