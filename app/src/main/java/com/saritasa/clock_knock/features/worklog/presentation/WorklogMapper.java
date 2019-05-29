package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;

import timber.log.Timber;

/**
 * Mapper class for transform objects between domain and presentation layer.
 */
public final class WorklogMapper{

    private WorklogMapper(){
    }

    /**
     * Maps single domain object into presentation object.
     *
     * @param aTasksDomain Domain object for mapping.
     * @return Presentation object.
     */
    @NonNull
    public static WorklogAdapterItem mapWorklogDomainToWorklogAdapterItem(@NonNull WorklogDomain aTasksDomain){
        WorklogAdapterItem worklogAdapterItem = new WorklogAdapterItem();
        worklogAdapterItem.setId(aTasksDomain.getId());
        worklogAdapterItem.setDescription(aTasksDomain.getDescription());
        worklogAdapterItem.setTimeSpentSeconds(aTasksDomain.getTimeSpentSeconds());
        worklogAdapterItem.setCreationDate(aTasksDomain.getCreationDate());
        return worklogAdapterItem;
    }

    /**
     * Maps single presentation object into domain object.
     *
     * @param aWorklogAdapterItem Presentation object for mapping.
     * @return Domain object.
     */
    @NonNull
    public static WorklogDomain mapWorklogDomainFromWorklogAdapterItem(@NonNull WorklogAdapterItem aWorklogAdapterItem){
        WorklogDomain worklogDomain = new WorklogDomain();
        worklogDomain.setId(aWorklogAdapterItem.getId());
        worklogDomain.setDescription(aWorklogAdapterItem.getDescription());
        worklogDomain.setTimeSpentSeconds(aWorklogAdapterItem.getTimeSpentSeconds());
        worklogDomain.setCreationDate(aWorklogAdapterItem.getCreationDate());
        return worklogDomain;
    }

}
