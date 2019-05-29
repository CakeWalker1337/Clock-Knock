package com.saritasa.clock_knock.features.session.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.util.Strings;

/**
 * An implementation class with the global repository functionality
 */
public class SessionRepositoryImpl implements SessionRepository{

    private PreferenceManager mPreferenceManager;

    /**
     * @param aPreferenceManager Preference manager
     */
    public SessionRepositoryImpl(PreferenceManager aPreferenceManager){
        mPreferenceManager = aPreferenceManager;
    }

    @Override
    public void clearAllData(){
        mPreferenceManager.clearAllData();
    }

    @Override
    public void saveStartTimestamp(final long aTimestamp){
        mPreferenceManager.saveStartTimestamp(aTimestamp);
    }

    @Override
    public long getStartTimestamp(){
        return mPreferenceManager.getStartTimestamp();
    }

    @Override
    public void saveTaskId(final long aTaskId){
        mPreferenceManager.saveTaskId(aTaskId);
    }

    @Override
    public void saveTaskKey(@NonNull final String aTaskKey){
        mPreferenceManager.saveTaskKey(aTaskKey);
    }

    @Override
    public long getTaskId(){
        return mPreferenceManager.getTaskId();
    }

    @Nullable
    @Override
    public String getTaskKey(){
        return mPreferenceManager.getTaskKey();
    }

    @Override
    public void clearTimerData(){
        mPreferenceManager.removeStartTimestamp();
        mPreferenceManager.removeTaskId();
        mPreferenceManager.removeTaskKey();
    }
}
