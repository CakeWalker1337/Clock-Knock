package com.saritasa.clock_knock.features.main.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;

public class MainRepositoryImpl extends BaseRepositoryImpl implements MainRepository{


    private ResourceManager mResourceManager;
    private PreferenceManager mPreferenceManager;

    /**
     * @param aResourceManager resource manager
     */
    public MainRepositoryImpl(@NonNull final ResourceManager aResourceManager, PreferenceManager aPreferenceManager){
        super(aResourceManager);
        mResourceManager = aResourceManager;
        mPreferenceManager = aPreferenceManager;
    }

    @Override
    public boolean checkAccessToken(){
        return mPreferenceManager.getAccessToken() != null;
    }
}
