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
    public void saveAccessToken(final String aAccessToken){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_ACCESS_TOKEN, aAccessToken).apply();
    }

    @Nullable
    @Override
    public String getAccessToken(){
        return mSharedPreferences.getString(Strings.PREFERENCE_ACCESS_TOKEN, null);
    }

    @Override
    public void saveUsername(final String aUsername){
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
    public void saveSecretToken(final String aSecretToken){
        mSharedPreferences.edit().putString(Strings.PREFERENCE_SECRET_TOKEN, aSecretToken).apply();
    }

    @Override
    public String getSecretToken(){
        return mSharedPreferences.getString(Strings.PREFERENCE_SECRET_TOKEN, null);
    }
}
