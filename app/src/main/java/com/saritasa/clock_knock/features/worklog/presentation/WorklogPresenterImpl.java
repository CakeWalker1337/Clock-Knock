package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.worklog.domain.WorklogDomain;
import com.saritasa.clock_knock.features.worklog.domain.WorklogInteractor;
import com.saritasa.clock_knock.util.DateTimeFormatter;
import com.saritasa.clock_knock.util.Strings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import timber.log.Timber;

/**
 * Presenter class realizes interacting of UI and model layers.
 */
@InjectViewState
public class WorklogPresenterImpl extends BasePresenterImpl<WorklogView> implements WorklogPresenter<WorklogView>{

    private WorklogInteractor mWorklogInteractor;
    private int mTotalTime = 0;

    /**
     * @param aWorklogInteractor provided interactor object.
     */
    public WorklogPresenterImpl(@NonNull WorklogInteractor aWorklogInteractor){
        mWorklogInteractor = aWorklogInteractor;
    }

    @Override
    public void attachView(@NonNull WorklogView aView){
        super.attachView(aView);
    }

    @Override
    public void detachView(@NonNull WorklogView aView){
        super.detachView(aView);
    }

    @Override
    public void onDataRequest(long aTaskId, @NonNull final String aTaskKey){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            ArrayList<WorklogDomain> worklogDomains = mWorklogInteractor.loadWorklog(aTaskId);
            ArrayList<WorklogAdapterItem> worklogAdapterItems = new ArrayList<>();
            for(WorklogDomain worklogDomain : worklogDomains){
                WorklogAdapterItem item = WorklogMapper.mapWorklogDomainToWorklogAdapterItem(worklogDomain);
                item.setTimeSpent(DateTimeFormatter.longTimeSecondsToText(item.getTimeSpentSeconds()));
                worklogAdapterItems.add(item);
            }
            worklogDomains.clear();

            getViewState().runTaskOnUiThread(() -> handleLoadWorklogsSuccess(worklogAdapterItems));
        });
    }

    @Override
    public void onWorklogAdd(long aTaskId, @NonNull String aDescription, int aTimeSpentSeconds){
        WorklogAdapterItem worklogAdapterItem = new WorklogAdapterItem();
        worklogAdapterItem.setDescription(aDescription);
        worklogAdapterItem.setTimeSpentSeconds(aTimeSpentSeconds);
        worklogAdapterItem.setTimeSpent(DateTimeFormatter.longTimeSecondsToText(aTimeSpentSeconds));
        worklogAdapterItem.setCreationDate(Calendar.getInstance().getTime());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            long id = mWorklogInteractor.createWorklog(aTaskId, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(worklogAdapterItem));

            getViewState().runTaskOnUiThread(() -> {
                worklogAdapterItem.setId(id);
                mWorklogInteractor.clearTimerData();
                mTotalTime += worklogAdapterItem.getTimeSpentSeconds();
                getViewState().showTotalTimeString(DateTimeFormatter.longTimeSecondsToText(mTotalTime));
                getViewState().addWorklogToList(worklogAdapterItem);
            });
        });
    }

    @Override
    public void onWorklogEdit(int aPosition, WorklogAdapterItem aOldItem, long aTaskId, @NonNull String aDescription, int aTimeSpentSeconds){
        aOldItem.setDescription(aDescription);
        int oldTime = aOldItem.getTimeSpentSeconds();
        aOldItem.setTimeSpentSeconds(aTimeSpentSeconds);
        aOldItem.setTimeSpent(DateTimeFormatter.longTimeSecondsToText(aTimeSpentSeconds));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            int result = mWorklogInteractor.updateWorklog(aTaskId, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(aOldItem));

            getViewState().runTaskOnUiThread(() -> {
                if(result == 0){
                    getViewState().showErrorMessage("Error in worklog editing!");
                    return;
                }
                mTotalTime += aTimeSpentSeconds - oldTime;
                getViewState().showTotalTimeString(DateTimeFormatter.longTimeSecondsToText(mTotalTime));
                getViewState().editWorklogInList(aPosition, aOldItem);
            });

        });
    }

    @Override
    public void onWorklogDelete(final int aPosition, long aTaskId, @NonNull final WorklogAdapterItem aWorklogItem){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            int result = mWorklogInteractor.deleteWorklog(aTaskId, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(aWorklogItem));
            getViewState().runTaskOnUiThread(() -> {
                if(result == 0){
                    getViewState().showErrorMessage("Error in worklog deleting!");
                    return;
                }
                mTotalTime -= aWorklogItem.getTimeSpentSeconds();
                getViewState().showTotalTimeString(DateTimeFormatter.longTimeSecondsToText(mTotalTime));
                getViewState().removeWorklogFromList(aPosition);
            });
        });
    }

    @Override
    public boolean isTimerActive(){
        return mWorklogInteractor.isTimerActive();
    }

    @Override
    public void onTimerTicked(final long aTime){
        String timeString = mWorklogInteractor.getFormattedTime(aTime);
        getViewState().setTimeToTimer(timeString);
    }

    @Override
    public int getHours(){
        return mWorklogInteractor.getHours();
    }

    @Override
    public int getMinutes(){
        return mWorklogInteractor.getMinutes();
    }

    @Override
    public void onStartButtonClicked(long aTaskId, @NonNull String aTaskKey){
        long timestamp = mWorklogInteractor.saveTimerData(aTaskId, aTaskKey);
        getViewState().startTimer(timestamp);
    }

    @Override
    public void onStopButtonClicked(){
        getViewState().tryToStopTimer();
    }

    @Override
    public void onActionGot(@NonNull final String aAction){
        if(aAction.equals(Strings.STOP_TIMER_ACTION)){
            getViewState().tryToStopTimer();
        }
    }

    @Override
    public long getTimerTask(){
        return mWorklogInteractor.getTimerTask();
    }

    /**
     * Updates recyclerview items. Calls only on success.
     *
     * @param aWorklogAdapterItems new list of data
     */
    private void handleLoadWorklogsSuccess(@NonNull List<WorklogAdapterItem> aWorklogAdapterItems){
        if(aWorklogAdapterItems.isEmpty()){
            getViewState().showNoWorklogMessageView();
            getViewState().hideWorklogView();
        } else{
            getViewState().showWorklogView();
            getViewState().hideNoWorklogMessageView();
        }

        mTotalTime = 0;

        for(WorklogAdapterItem item : aWorklogAdapterItems){
            mTotalTime += item.getTimeSpentSeconds();
        }

        getViewState().showTotalTimeString(DateTimeFormatter.longTimeSecondsToText(mTotalTime));
        getViewState().setWorklogList(aWorklogAdapterItems);
    }

    /**
     * Shows information about error. Calls only on failure.
     *
     * @param aThrowable exception.
     */
    private void handleError(@NonNull Throwable aThrowable){
        Timber.e(aThrowable);
        getViewState().showErrorMessage(aThrowable.getMessage());
    }

}
