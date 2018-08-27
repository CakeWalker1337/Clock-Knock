package com.saritasa.clock_knock.features.login.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.api.RestApi;
import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.base.data.ResourceManager;
import com.saritasa.clock_knock.features.login.domain.UsernameDomain;

import io.reactivex.Single;

/**
 * An implementation repository class for executing data working methods while logging in
 */
public class LoginRepositoryImpl extends BaseRepositoryImpl implements LoginRepository{

    private ResourceManager mResourceManager;

    private RestApi mRestApi;

    /**
     * @param aResourceManager Resource manager
     * @param aRestApi Rest API interface
     */
    public LoginRepositoryImpl(@NonNull final ResourceManager aResourceManager, @NonNull RestApi aRestApi){
        super(aResourceManager);
        mResourceManager = aResourceManager;
        mRestApi = aRestApi;
    }

    @NonNull
    @Override
    public Single<UsernameDomain> getUsername(){
        return mRestApi.getUsername().map(LoginEntityMapper::mapUsernameFromEntity);
    }
}
