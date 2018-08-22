package com.saritasa.clock_knock.base.domain;

import com.saritasa.clock_knock.base.data.BaseRepository;

/**
 * Base interactor class working with repository, which has been inherited by BaseRepository.
 *
 * @param <REPOSITORY>
 */
public class BaseInteractorImpl<REPOSITORY extends BaseRepository> implements BaseInteractor{

    protected REPOSITORY mRepository;

    public BaseInteractorImpl(REPOSITORY aRepository){
        mRepository = aRepository;
    }

    /**
     * Gets string resource from resource manager.
     * @param aId id of string resource
     * @param aParams additive parameters
     * @return String by resource id.
     */
    public String getStringResource(int aId, Object... aParams){
        return mRepository.getStringResource(aId, aParams);
    }

}
