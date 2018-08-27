package com.saritasa.clock_knock.base.data;

import android.support.annotation.Nullable;

/**
 * Interface of base repository.
 */
public interface BaseRepository{

    /**
     * Gets string resource by id and params.
     *
     * @param aResourceId resource id.
     * @param aParams other params.
     * @return String by resource id.
     */
    @Nullable
    String getStringResource(int aResourceId, Object... aParams);

}
