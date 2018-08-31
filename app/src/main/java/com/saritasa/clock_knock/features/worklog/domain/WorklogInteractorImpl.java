package com.saritasa.clock_knock.features.worklog.domain;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.domain.BaseInteractorImpl;
import com.saritasa.clock_knock.features.session.data.SessionRepository;
import com.saritasa.clock_knock.features.worklog.data.WorklogRepository;
import com.saritasa.clock_knock.util.Constants;
import com.saritasa.clock_knock.util.Strings;

import org.apache.commons.lang3.time.DurationFormatUtils;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Interactor class of worklog module. Processes data which comes from the data layer.
 */
public class WorklogInteractorImpl extends BaseInteractorImpl<WorklogRepository> implements WorklogInteractor{

    public static final int LESS = -1;
    public static final int MORE = 1;
    public static final String USER_NAME = "maxim.kovalev";

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
    public Observable<WorklogDomain> loadWorklog(final String aTaskKey){
        return mRepository.loadWorklog(aTaskKey)
                .filter(aWorklogDomain -> aWorklogDomain.getAuthorsKey().equals(mRepository.getUsername()))
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
    public long saveTimerData(@NonNull final String aTaskKey){
        long timestamp = System.currentTimeMillis();
        mSessionRepository.saveStartTimestamp(timestamp);
        mSessionRepository.saveTaskId(aTaskKey);
        return timestamp;
    }

    @Override
    public void clearTimerData(){
        mSessionRepository.clearTimerData();
    }

    @Override
    public String getTimerTask(){
        return mSessionRepository.getTaskId();
    }
}
