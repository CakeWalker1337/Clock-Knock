package com.saritasa.clock_knock.features.worklog.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;
import com.saritasa.clock_knock.util.GsonConverter;
import com.saritasa.moxytest.utils.DateFormatter;

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
        worklogDomain.setComment(aWorklogEntity.getComment());
        worklogDomain.setAuthorsKey(aWorklogEntity.getAuthor().getKey());
        try{
            Timber.d(DateFormatter.getInstance().format(Calendar.getInstance().getTime()));
            worklogDomain.setCreationDate(DateFormatter.getInstance().parse(aWorklogEntity.getCreationDate()));
        } catch(ParseException aE){
            aE.printStackTrace();
        }
        worklogDomain.setTimeSpentSeconds(Integer.parseInt(aWorklogEntity.getTimeSpentSeconds()));
        worklogDomain.setTimeSpent(aWorklogEntity.getTimeSpent());
        return worklogDomain;
    }

    /**
     * Maps worklog domain object to worklog entity object.
     *
     * @param aWorklogDomain domain object for mapping.
     * @return entity object.
     */
    @NonNull
    public static WorklogOutputEntity mapWorklogEntityFromWorklogDomain(@NonNull WorklogDomain aWorklogDomain){
        WorklogOutputEntity worklogOutputEntity = new WorklogOutputEntity();
        worklogOutputEntity.setId(aWorklogDomain.getId());
        worklogOutputEntity.setComment(aWorklogDomain.getComment());
        worklogOutputEntity.setTimeSpentSeconds(aWorklogDomain.getTimeSpentSeconds());
        worklogOutputEntity.setUpdated(DateFormatter.getInstance().format(Calendar.getInstance().getTime()));
        return worklogOutputEntity;
    }

    /**
     * Maps worklog entity object to JSON format.
     *
     * @param aWorklogOutputEntity entity object to mapping
     * @return JSON string
     */
    @Nullable
    public static String mapWorklogEntityToJsonObject(@NonNull WorklogOutputEntity aWorklogOutputEntity){
        return GsonConverter.getInstance().toJson(aWorklogOutputEntity);
    }

}
