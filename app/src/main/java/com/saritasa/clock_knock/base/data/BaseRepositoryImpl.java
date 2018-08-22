package com.saritasa.clock_knock.base.data;

/**
 * Base repository class. Contains resource manager.
 */
public class BaseRepositoryImpl implements BaseRepository{

    ResourceManager mResourceManager;

    public BaseRepositoryImpl(ResourceManager aResourceManager){
        mResourceManager = aResourceManager;
    }

    @Override
    public String getStringResource(final int aId, final Object... aParams){
        return mResourceManager.getStringResource(aId, aParams);
    }
}
