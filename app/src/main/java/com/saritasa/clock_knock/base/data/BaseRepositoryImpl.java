package com.saritasa.clock_knock.base.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A class with implementation of basic repository methods
 */
public class BaseRepositoryImpl implements BaseRepository{


    private final ResourceManager mResourceManager;

    /**
     * @param aResourceManager Resource manager
     */
    public BaseRepositoryImpl(@NonNull ResourceManager aResourceManager){
        mResourceManager = aResourceManager;
    }

    @NonNull
    @Override
    public String getStringResource(final int aResourceId, @Nullable final Object... aFormatArgs){
        return mResourceManager.getStringResource(aResourceId, aFormatArgs);
    }
}
