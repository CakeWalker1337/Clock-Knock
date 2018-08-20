package com.saritasa.clock_knock.base.data;

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
