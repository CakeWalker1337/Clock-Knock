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
    public void saveAccessToken(@NonNull final String aAccessToken){
        mPreferenceManager.saveAccessToken(aAccessToken);
    }

    @Nullable
    @Override
    public String getAccessToken(){
        return mPreferenceManager.getAccessToken();
    }

    @Override
    public void saveUsername(@NonNull final String aUsername){
        mPreferenceManager.saveUsername(aUsername);
    }

    @Nullable
    @Override
    public String getUsername(){
        return mPreferenceManager.getUsername();
    }


    @Override
    public void clearAllData(){
        mPreferenceManager.clearAllData();
    }

    @Override
    public void saveSecretToken(@NonNull final String aSecretToken){
        mPreferenceManager.saveSecretToken(aSecretToken);
    }

    @Nullable
    @Override
    public String getSecretToken(){
        return mPreferenceManager.getSecretToken();
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
    public void saveTaskId(@NonNull final String aTaskId){
        mPreferenceManager.saveTaskId(aTaskId);
    }

    @Nullable
    @Override
    public String getTaskId(){
        return mPreferenceManager.getTaskId();
    }

    @NonNull
    @Override
    public String getBaseUrl(){
        return BuildConfig.BASE_URL;
    }

    @NonNull
    @Override
    public String getPrivateKey(){
        return Strings.PRIVATE_KEY;
    }

    @NonNull
    @Override
    public String getConsumerKey(){
        return Strings.CONSUMER_KEY;
    }

    @NonNull
    @Override
    public long getNetworkTimeout(){
        return BuildConfig.NETWORK_TIMEOUT_MILLIS;
    }

    @NonNull
    @Override
    public long getConnectTimeout(){
        return BuildConfig.CONNECT_TIMEOUT_MILLIS;
    }

    @NonNull
    @Override
    public int getRetryRequestCount(){
        return BuildConfig.COUNT_RETRY_REQUEST;
    }
}
