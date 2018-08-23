package com.saritasa.clock_knock.base.data;

import android.os.Build;

import com.saritasa.clock_knock.BuildConfig;
import com.saritasa.clock_knock.util.Strings;

/**
 * An implementation class with the global repository functionality
 */
public class GlobalRepositoryImpl implements GlobalRepository{

    private PreferenceManager mPreferenceManager;

    /**
     * @param aPreferenceManager Preference manager
     */
    public GlobalRepositoryImpl(PreferenceManager aPreferenceManager){
        mPreferenceManager = aPreferenceManager;
    }

    @Override
    public void saveAccessToken(final String aAccessToken){
        mPreferenceManager.saveAccessToken(aAccessToken);
    }

    @Override
    public String getAccessToken(){
        return mPreferenceManager.getAccessToken();
    }

    @Override
    public void saveUsername(final String aUsername){
        mPreferenceManager.saveUsername(aUsername);
    }

    @Override
    public String getUsername(){
        return mPreferenceManager.getUsername();
    }

    @Override
    public void clearAllData(){
        mPreferenceManager.clearAllData();
    }

    @Override
    public void saveSecretToken(final String aSecretToken){
        mPreferenceManager.saveSecretToken(aSecretToken);
    }

    @Override
    public String getSecretToken(){
        return mPreferenceManager.getSecretToken();
    }

    @Override
    public String getBaseUrl(){
        return BuildConfig.BASE_URL;
    }

    @Override
    public String getPrivateKey(){
        return Strings.PRIVATE_KEY;
    }

    @Override
    public String getConsumerKey(){
        return Strings.CONSUMER_KEY;
    }

    @Override
    public long getNetworkTimeout(){
        return BuildConfig.NETWORK_TIMEOUT_MILLIS;
    }

    @Override
    public long getConnectTimeout(){
        return BuildConfig.CONNECT_TIMEOUT_MILLIS;
    }

    @Override
    public int getRetryRequestCount(){
        return BuildConfig.COUNT_RETRY_REQUEST;
    }
}
