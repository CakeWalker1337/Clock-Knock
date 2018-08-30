package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Repository interface provides worklog methods to repository object.
 */
public interface WorklogRepository extends BaseRepository{

    /**
     * Loads worklogs from API. Maps raw data to Domain layer model objects.
     *
     * @param aTaskKey task key for loading data (Ex: MISC-303).
     * @return observable with domain objects.
     */
    @NonNull
    Observable<WorklogDomain> loadWorklog(@NonNull String aTaskKey);

    /**
     * Creates new worklog on JIRA server through API. Maps data from domain layer to entity
     * form and formats in into the JSON format.
     *
     * @param aTaskKey task key for creating new worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     * @return single domain object equals of created object on JIRA server.
     */
    @NonNull
    Single<WorklogDomain> createWorklog(@NonNull String aTaskKey, @NonNull WorklogDomain aWorklogDomain);

    /**
     * Updates worklog on JIRA server through API. Maps data from domain layer to entity
     * form and formats in into the JSON format.
     *
     * @param aTaskKey task key for updating worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     */
    void saveWorklog(@NonNull String aTaskKey, @NonNull WorklogDomain aWorklogDomain);

}
