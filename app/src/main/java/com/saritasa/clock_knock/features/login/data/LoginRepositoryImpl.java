package com.saritasa.clock_knock.features.login.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;

public class LoginRepositoryImpl extends BaseRepositoryImpl implements LoginRepository{

    private PreferenceManager mPreferenceManager;

    private ResourceManager mResourceManager;

    /**
     * @param aResourceManager resource manager
     */
    public LoginRepositoryImpl(@NonNull final ResourceManager aResourceManager, PreferenceManager aPreferenceManager){
        super(aResourceManager);

        mResourceManager = aResourceManager;
        mPreferenceManager = aPreferenceManager;
    }


}
