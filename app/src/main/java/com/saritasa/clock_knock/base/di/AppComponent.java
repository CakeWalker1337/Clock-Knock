package com.saritasa.clock_knock.base.di;

import android.arch.persistence.room.Database;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.features.main.di.MainComponent;
import com.saritasa.clock_knock.features.session.di.SessionModule;
import com.saritasa.clock_knock.features.tasks.di.TasksComponent;
import com.saritasa.clock_knock.features.worklog.di.WorklogComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main component contains feature subcomponents and API and App modules
 */
@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class, SessionModule.class})
public interface AppComponent{

    /**
     * Gets the Main component builder
     *
     * @return Main component builder
     */
    @NonNull
    MainComponent.Builder mainComponentBuilder();


    /**
     * Injects App component to the App class
     * @param aApp App class object
     */
    void inject(App aApp);

    /**
     * Builder of tasks subcomponent module.
     * @return tasks component builder.
     */
    @NonNull
    TasksComponent.Builder tasksComponentBuilder();

    /**
     * Builder of worklog subcomponent module.
     *
     * @return worklog component builder.
     */
    @NonNull
    WorklogComponent.Builder worklogComponentBuilder();

}
