package com.saritasa.clock_knock.base.di;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.local.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule{

    private Context mContext;

    public DatabaseModule(@NonNull Context aContext){
        mContext = aContext;
    }

    /**
     * Provides the Database API interfaces
     *
     * @return Database API abstract class to get database interfaces
     */
    @NonNull
    @Provides
    @Singleton
    public AppDatabase provideDatabaseApi(){
        return Room.databaseBuilder(mContext, AppDatabase.class, "clock-knock").build();
    }
}
