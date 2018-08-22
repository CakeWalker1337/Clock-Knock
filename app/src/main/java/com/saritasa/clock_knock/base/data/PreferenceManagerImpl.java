package com.saritasa.clock_knock.base.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference manager class. Contains shared preference object.
 */
public class PreferenceManagerImpl implements PreferenceManager{

    private SharedPreferences mSharedPreferences;

    public PreferenceManagerImpl(Context aContext){
        mSharedPreferences = aContext.getSharedPreferences("clock_knock", Context.MODE_PRIVATE);
    }

}
