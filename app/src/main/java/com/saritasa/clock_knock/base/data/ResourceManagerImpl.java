package com.saritasa.clock_knock.base.data;

import android.content.Context;

public class ResourceManagerImpl implements ResourceManager{

    Context mContext;

    public ResourceManagerImpl(Context aContext){
        mContext = aContext;
    }

    @Override
    public String getStringResource(int aResourceId, Object... aParams){
        return mContext.getString(aResourceId, aParams);
    }
}
