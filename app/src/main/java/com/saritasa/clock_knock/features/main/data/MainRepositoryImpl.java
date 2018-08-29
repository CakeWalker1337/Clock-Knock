package com.saritasa.clock_knock.features.main.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.base.data.ResourceManager;

/**
 * An implementation repository class for executing data working main methods
 */
public class MainRepositoryImpl extends BaseRepositoryImpl implements MainRepository{

    private ResourceManager mResourceManager;

    private SessionRepository mSessionRepository;

    /**
     * @param aResourceManager Resource manager
     * @param aSessionRepository Global repository
     */
    public MainRepositoryImpl(@NonNull final ResourceManager aResourceManager, @NonNull SessionRepository aSessionRepository){
        super(aResourceManager);
        mResourceManager = aResourceManager;
        mSessionRepository = aSessionRepository;
    }
}
