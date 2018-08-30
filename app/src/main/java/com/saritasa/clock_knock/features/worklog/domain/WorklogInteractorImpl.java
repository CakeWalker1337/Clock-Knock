package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.worklog.data.WorklogRepository;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Interactor class of worklog module. Processes data which comes from the data layer.
 */
public class WorklogInteractorImpl extends BaseInteractorImpl<WorklogRepository> implements WorklogInteractor{

    public static final int LESS = -1;
    public static final int MORE = 1;
    public static final String USER_NAME = "maxim.kovalev";

    /**
     * @param aRepository provided repository object.
     */
    public WorklogInteractorImpl(final WorklogRepository aRepository){
        super(aRepository);
    }

    @NonNull
    @Override
    public Observable<WorklogDomain> loadWorklog(final String aTaskKey){
        return mRepository.loadWorklog(aTaskKey)
                .filter(aWorklogDomain -> aWorklogDomain.getAuthorsKey().equals(USER_NAME))
                .filter(aWorklogDomain -> aWorklogDomain.getCreationDate() != null)
                .sorted((aWorklogDomain1, aWorklogDomain2) -> {
                    if(aWorklogDomain1.getCreationDate().after(aWorklogDomain2.getCreationDate())){
                        return LESS;
                    }
                    return MORE;
                });
    }

    @NonNull
    @Override
    public Maybe<WorklogDomain> createWorklog(@NonNull String aTaskKey, @NonNull final WorklogDomain aWorklogDomain){
        return mRepository.createWorklog(aTaskKey, aWorklogDomain)
                .filter(aDomain -> aDomain.getCreationDate() != null);
    }

    @Override
    public void saveWorklog(@NonNull String aTaskKey, @NonNull final WorklogDomain aWorklogDomain){
        mRepository.saveWorklog(aTaskKey, aWorklogDomain);
    }

}
