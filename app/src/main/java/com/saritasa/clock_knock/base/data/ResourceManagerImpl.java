package com.saritasa.clock_knock.base.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import javax.inject.Inject;

public class ResourceManagerImpl implements ResourceManager{

    private final Context mContext;

    /**
     * @param aContext context
     */
    @Inject
    public ResourceManagerImpl(@NonNull Context aContext){
        mContext = aContext;
    }

    @NonNull
    @Override
    public String getStringResource(@StringRes int aResourceId, @Nullable Object... aFormatArgs){
        return mContext.getString(aResourceId, aFormatArgs);
    }
}
