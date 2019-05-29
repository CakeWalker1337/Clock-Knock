package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.data.BaseRepository;
import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;

import java.util.ArrayList;

/**
 * Repository interface provides worklog methods to repository object.
 */
public interface WorklogRepository extends BaseRepository{

    /**
     * Loads worklogs from API. Maps raw data to Domain layer model objects.
     *
     * @param aTaskId task key for loading data (Ex: MISC-303).
     * @return observable with domain objects.
     */
    @NonNull
    ArrayList<WorklogDomain> loadWorklog(long aTaskId);

    /**
     * Creates new worklog on JIRA server through API. Maps data from domain layer to entity
     * form and formats in into the JSON format.
     *
     * @param aTaskId task key for creating new worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     * @return single domain object equals of created object on JIRA server.
     */
    long createWorklog(long aTaskId, @NonNull WorklogDomain aWorklogDomain);

    /**
     * Updates worklog on JIRA server through API. Maps data from domain layer to entity
     * form and formats in into the JSON format.
     *
     * @param aTaskId task key for updating worklog (Ex: MISC-303).
     * @param aWorklogDomain domain object of worklog.
     */
    int updateWorklog(long aTaskId, @NonNull WorklogDomain aWorklogDomain);

    int deleteWorklog(long aTaskId, @NonNull WorklogDomain aWorklogDomain);
}
