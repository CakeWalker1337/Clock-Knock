package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;
import com.saritasa.clock_knock.util.DateTimeFormatter;

import java.text.ParseException;
import java.util.Calendar;

import timber.log.Timber;

/**
 * Mapper class for mapping objects between data and domain layers.
 */
public class WorklogEntityMapper{

    /**
     * Maps worklog entity object to worklog domain object.
     *
     * @param aWorklogEntity entity object for mapping.
     * @return domain object.
     */
    @NonNull
    public static WorklogDomain mapWorklogEntityToWorklogDomain(@NonNull WorklogEntity aWorklogEntity){
        WorklogDomain worklogDomain = new WorklogDomain();
        worklogDomain.setId(aWorklogEntity.getId());
        worklogDomain.setDescription(aWorklogEntity.getDescription());
        try{
            worklogDomain.setCreationDate(DateTimeFormatter.getInstance().parse(aWorklogEntity.getCreationDate()));
        } catch(ParseException aE){
            aE.printStackTrace();
        }
        worklogDomain.setTimeSpentSeconds(Integer.parseInt(String.valueOf(aWorklogEntity.getTimeSpentSeconds())));
        return worklogDomain;
    }

    /**
     * Maps worklog domain object to worklog entity object.
     *
     * @param aWorklogDomain domain object for mapping.
     * @return entity object.
     */
    @NonNull
    public static WorklogEntity mapWorklogEntityFromWorklogDomain(@NonNull WorklogDomain aWorklogDomain){
        WorklogEntity worklogEntity = new WorklogEntity();
        worklogEntity.setId(aWorklogDomain.getId());
        worklogEntity.setDescription(aWorklogDomain.getDescription());
        worklogEntity.setTimeSpentSeconds(aWorklogDomain.getTimeSpentSeconds());
        worklogEntity.setCreationDate(DateTimeFormatter.getInstance().format(aWorklogDomain.getCreationDate()));
        return worklogEntity;
    }
}
