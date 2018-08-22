package com.saritasa.clock_knock.features.login.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.PreferenceManager;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import io.reactivex.Single;

public class LoginRepositoryImpl extends BaseRepositoryImpl implements LoginRepository{

    private PreferenceManager mPreferenceManager;

    private ResourceManager mResourceManager;

    private RestApi mRestApi;

    /**
     * @param aResourceManager resource manager
     */
    public LoginRepositoryImpl(@NonNull final ResourceManager aResourceManager, PreferenceManager aPreferenceManager, RestApi aRestApi){
        super(aResourceManager);

        mResourceManager = aResourceManager;
        mPreferenceManager = aPreferenceManager;
        mRestApi = aRestApi;
    }

    @Override
    public Single<UsernameDomain> getUsername(){
        return mRestApi.getUsername().map(LoginEntityMapper::mapUsernameFromEntity);
    }

    @Override
    public void saveUsername(final String aUsername){
        mPreferenceManager.saveUsername(aUsername);
    }

    @Override
    public boolean isAccessTokenExist(){
        return mPreferenceManager.getAccessToken() != null;
    }
}
