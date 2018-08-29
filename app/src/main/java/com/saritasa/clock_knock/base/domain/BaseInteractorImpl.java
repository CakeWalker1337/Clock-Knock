package com.saritasa.clock_knock.base.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;

/**
 * An implementation for BaseInteractor interface typed for repositories
 *
 * @param <REPOSITORY> Any repository class which extends the BaseRepository class
 */
public class BaseInteractorImpl<REPOSITORY extends BaseRepository> implements BaseInteractor{

    protected REPOSITORY mRepository;

    /**
     * @param aRepository Repository object
     */
    public BaseInteractorImpl(@NonNull REPOSITORY aRepository) {
        mRepository = aRepository;
    }

    /**
     * Gets the string by its resource id
     *
     * @param aId Resource id
     * @param aParams Parameters array
     * @return String by resource id
     */
    @NonNull
    public String getStringResource(int aId, Object... aParams){
        return mRepository.getStringResource(aId, aParams);
    }
}
