package com.saritasa.clock_knock.features.main.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepositoryImpl;
import com.saritasa.clock_knock.base.data.GlobalRepository;
import com.saritasa.clock_knock.base.data.ResourceManager;

/**
 * An implementation repository class for executing data working main methods
 */
public class MainRepositoryImpl extends BaseRepositoryImpl implements MainRepository{

    private ResourceManager mResourceManager;

    private GlobalRepository mGlobalRepository;

    /**
     * @param aResourceManager Resource manager
     * @param aGlobalRepository Global repository
     */
    public MainRepositoryImpl(@NonNull final ResourceManager aResourceManager, GlobalRepository aGlobalRepository){
        super(aResourceManager);
        mResourceManager = aResourceManager;
        mGlobalRepository = aGlobalRepository;
    }
}
