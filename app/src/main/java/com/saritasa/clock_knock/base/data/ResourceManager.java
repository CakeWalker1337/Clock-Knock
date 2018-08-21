package com.saritasa.clock_knock.base.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

public interface ResourceManager{

    /**
     * Get string by android resource id
     *
     * @param aResourceId resource id
     * @return string
     */
    @NonNull
    String getStringResource(@StringRes int aResourceId, @Nullable Object... aFormatArgs);
}
