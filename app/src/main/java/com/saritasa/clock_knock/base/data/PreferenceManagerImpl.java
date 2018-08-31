package com.saritasa.clock_knock.base.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.R;
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
    public void saveAccessToken(@NonNull final String aAccessToken){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_ACCESS_TOKEN, aAccessToken).apply();
    }

    @Nullable
    @Override
    public String getAccessToken(){
        return mSharedPreferences.getString(Strings.PREFERENCE_ACCESS_TOKEN, null);
    }

    @Override
    public void saveUsername(@NonNull final String aUsername){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_USERNAME, aUsername).apply();
    }

    @Nullable
    @Override
    public String getUsername(){
        return mSharedPreferences.getString(Strings.PREFERENCE_USERNAME, null);
    }

    @Override
    public void clearAllData(){
        mSharedPreferences.edit().clear().apply();
    }

    @Override
    public void saveSecretToken(@NonNull final String aSecretToken){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_SECRET_TOKEN, aSecretToken).apply();
    }

    @Nullable
    @Override
    public String getSecretToken(){
        return mSharedPreferences.getString(Strings.PREFERENCE_SECRET_TOKEN, null);
    }

    @Override
    public long getStartTimestamp(){
        return mSharedPreferences.getLong(Strings.PREFERENCE_START_TIMESTAMP, 0);
    }

    @Override
    public void saveStartTimestamp(long aTimestamp){
        mSharedPreferences.edit().putLong(Strings.PREFERENCE_SECRET_TOKEN, aTimestamp).apply();
    }

    @Nullable
    @Override
    public String getTaskId(){
        return mSharedPreferences.getString(Strings.PREFERENCE_TASK_ID, null);
    }

    @Override
    public void saveTaskId(@NonNull String aTaskId){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_TASK_ID, aTaskId).apply();
    }
}
