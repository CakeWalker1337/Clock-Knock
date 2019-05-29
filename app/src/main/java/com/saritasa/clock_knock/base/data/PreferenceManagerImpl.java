package com.saritasa.clock_knock.base.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.util.Constants;
import com.saritasa.clock_knock.util.Strings;

/**
 * An implementation class with the SharedPreferences operations
 */
public class PreferenceManagerImpl implements PreferenceManager{

    private SharedPreferences mSharedPreferences;

    /**
     * @param aContext Context
     */
    public PreferenceManagerImpl(@NonNull Context aContext){
        String appName = aContext.getString(R.string.app_name);
        mSharedPreferences = aContext.getSharedPreferences(appName, Context.MODE_PRIVATE);
    }

    @Override
    public void clearAllData(){
        mSharedPreferences.edit().clear().apply();
    }

    @Override
    public long getStartTimestamp(){
        return mSharedPreferences.getLong(Strings.PREFERENCE_START_TIMESTAMP, Constants.UNDEFINED_VALUE);
    }

    @Override
    public void saveStartTimestamp(long aTimestamp){
        mSharedPreferences.edit().putLong(Strings.PREFERENCE_START_TIMESTAMP, aTimestamp).apply();
    }

    @Override
    public long getTaskId(){
        return mSharedPreferences.getLong(Strings.PREFERENCE_TASK_ID, -1);
    }

    @Nullable
    @Override
    public String getTaskKey(){
        return mSharedPreferences.getString(Strings.PREFERENCE_TASK_KEY, null);
    }

    @Override
    public void removeStartTimestamp(){
        mSharedPreferences.edit().remove(Strings.PREFERENCE_START_TIMESTAMP).apply();
    }

    @Override
    public void removeTaskId(){
        mSharedPreferences.edit().remove(Strings.PREFERENCE_TASK_ID).apply();
    }

    @Override
    public void removeTaskKey(){
        mSharedPreferences.edit().remove(Strings.PREFERENCE_TASK_KEY).apply();
    }

    @Override
    public void saveTaskId(long aTaskId){
        mSharedPreferences.edit().putLong(Strings.PREFERENCE_TASK_ID, aTaskId).apply();
    }

    @Override
    public void saveTaskKey(@NonNull String aTaskKey){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_TASK_KEY, aTaskKey).apply();
    }
}
