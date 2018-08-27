package com.saritasa.clock_knock.base.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.saritasa.clock_knock.R;

/**
 * Preference manager class. Contains shared preference object.
 */
public class PreferenceManagerImpl implements PreferenceManager{

    private SharedPreferences mSharedPreferences;

    public PreferenceManagerImpl(Context aContext){
        String appName = aContext.getString(R.string.app_name);
        mSharedPreferences = aContext.getSharedPreferences(appName, Context.MODE_PRIVATE);
    }

}
