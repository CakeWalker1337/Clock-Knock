package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.worklog.data.WorklogRepository;
import com.saritasa.clock_knock.util.Constants;
import com.saritasa.clock_knock.util.DateTimeFormatter;
import com.saritasa.clock_knock.util.Strings;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Interactor class of worklog module. Processes data which comes from the data layer.
 */
public class WorklogInteractorImpl extends BaseInteractorImpl<WorklogRepository> implements WorklogInteractor{

    public static final int LESS = -1;
    public static final int MORE = 1;

    private SessionRepository mSessionRepository;

    /**
     * @param aRepository provided repository object.
     */
    public WorklogInteractorImpl(@NonNull final WorklogRepository aRepository, SessionRepository aSessionRepository){
        super(aRepository);
        mSessionRepository = aSessionRepository;
    }

    @NonNull
    @Override
    public ArrayList<WorklogDomain> loadWorklog(final long aTaskId){
        ArrayList<WorklogDomain> worklogDomains = mRepository.loadWorklog(aTaskId);

        Collections.sort(worklogDomains, (aWorklogDomain1, aWorklogDomain2) -> {
            if(aWorklogDomain1.getCreationDate().after(aWorklogDomain2.getCreationDate())){
                return LESS;
            }
            return MORE;
        });
        return worklogDomains;
    }

    @Override
    public long createWorklog(long aTaskId, @NonNull final WorklogDomain aWorklogDomain){
        return mRepository.createWorklog(aTaskId, aWorklogDomain);
    }

    @Override
    public int updateWorklog(long aTaskId, @NonNull final WorklogDomain aWorklogDomain){

        return mRepository.updateWorklog(aTaskId, aWorklogDomain);
    }

    @Override
    public int deleteWorklog(long aTaskId, @NonNull final WorklogDomain aWorklogDomain){
        return mRepository.deleteWorklog(aTaskId, aWorklogDomain);
    }

    @Override
    public boolean isTimerActive(){
        return mSessionRepository.getStartTimestamp() != Constants.UNDEFINED_VALUE;
    }

    @NonNull
    @Override
    public String getFormattedTime(final long aTime){
        return DurationFormatUtils.formatDuration(aTime, Strings.TIME_PATTERN);
    }

    @Override
    public int getHours(){
        long startTime = mSessionRepository.getStartTimestamp();
        long interval = System.currentTimeMillis() - startTime;
        return (int) interval / Constants.ONE_HOUR_MILLIS;
    }

    @Override
    public int getMinutes(){
        long startTime = mSessionRepository.getStartTimestamp();
        long interval = System.currentTimeMillis() - startTime;
        return (int) interval % Constants.ONE_HOUR_MILLIS / Constants.ONE_MINUTE_MILLIS;
    }

    @Override
    public long saveTimerData(long aTaskId, @NonNull final String aTaskKey){
        long timestamp = System.currentTimeMillis();
        mSessionRepository.saveStartTimestamp(timestamp);
        mSessionRepository.saveTaskId(aTaskId);
        mSessionRepository.saveTaskKey(aTaskKey);
        return timestamp;
    }

    @Override
    public void clearTimerData(){
        mSessionRepository.clearTimerData();
    }

    @Override
    public long getTimerTask(){
        return mSessionRepository.getTaskId();
    }
}
