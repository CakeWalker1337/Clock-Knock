package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.worklog.domain.WorklogInteractor;
import com.saritasa.clock_knock.util.Strings;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import okhttp3.Interceptor;
import timber.log.Timber;

/**
 * Presenter class realizes interacting of UI and model layers.
 */
@InjectViewState
public class WorklogPresenterImpl extends BasePresenterImpl<WorklogView> implements WorklogPresenter<WorklogView>{

    private WorklogInteractor mWorklogInteractor;

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
    public void onDataRequest(@NonNull final String aTaskKey){
        getViewState().showLoadingProgress();
        getViewState().updateActivityTitle(aTaskKey);
        Disposable disposable = mWorklogInteractor.loadWorklog(aTaskKey)
                .map(WorklogMapper::mapWorklogDomainToWorklogAdapterItem)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getViewState().hideLoadingProgress())
                .subscribe(this::handleLoadWorklogsSuccess,
                           this::handleLoadWorklogsError);
        unsubscribeOnDestroy(disposable);
    }

    @Override
    public void onTimerStopped(@NonNull String aTaskKey, @NonNull String aDescription, int aTimeSpentSeconds){
        getViewState().showLoadingProgress();
        WorklogAdapterItem adapterItem = new WorklogAdapterItem();
        adapterItem.setDescription(aDescription);
        adapterItem.setTimeSpentSeconds(aTimeSpentSeconds);
        Disposable disposable = mWorklogInteractor.createWorklog(aTaskKey, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(adapterItem))
                .map(WorklogMapper::mapWorklogDomainToWorklogAdapterItem)
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getViewState().hideLoadingProgress())
                .subscribe(this::handleCreateWorklogSuccess, this::handleCreateWorklogError, () -> {
                    throw new Exception("Worklog fetching error.");
                });
        unsubscribeOnDestroy(disposable);

        mWorklogInteractor.clearTimerData();
    }

    @Override
    public void onWorklogClicked(@NonNull String aTaskKey, @NonNull WorklogAdapterItem aWorklogAdapterItem){
        mWorklogInteractor.saveWorklog(aTaskKey, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(aWorklogAdapterItem));
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
    public void onStartButtonClicked(@NonNull String aTaskKey){
        long timestamp = mWorklogInteractor.saveTimerData(aTaskKey);
        getViewState().startTimer(timestamp);
    }

    @Override
    public void onStopButtonClicked(){
        getViewState().tryToStopTimer();
    }

    @Override
    public void onActionGot(@NonNull final String aAction){
        if (aAction.equals(Strings.STOP_TIMER_ACTION)) {
            getViewState().tryToStopTimer();
        }
    }

    @Nullable
    @Override
    public String getTimerTask(){
        return mWorklogInteractor.getTimerTask();
    }

    /**
     * Updates recyclerview items. Calls only on success.
     *
     * @param aWorklogAdapterItems new list of data
     */
    private void handleLoadWorklogsSuccess(@NonNull List<WorklogAdapterItem> aWorklogAdapterItems){
        if(aWorklogAdapterItems.isEmpty()){

            Timber.d("Array is empty");
            getViewState().showNoWorklogMessageView();
            getViewState().hideWorklogView();
        } else{
            getViewState().showWorklogView();
            getViewState().hideNoWorklogMessageView();
        }
        Timber.d("Worklog was loaded");
        getViewState().updateWorklogList(aWorklogAdapterItems);
    }

    /**
     * Shows information about error. Calls only on failure.
     *
     * @param aThrowable exception.
     */
    private void handleLoadWorklogsError(@NonNull Throwable aThrowable){
        Timber.e(aThrowable);
        getViewState().showErrorMessage(aThrowable.getMessage());
    }

    /**
     * Updates recyclerview items. Calls only on success.
     *
     * @param aWorklogAdapterItem new list of data
     */
    private void handleCreateWorklogSuccess(@NonNull WorklogAdapterItem aWorklogAdapterItem){
        getViewState().showWorklogView();
        getViewState().hideNoWorklogMessageView();
        Timber.d("Worklog was loaded");
        getViewState().addWorklogToList(aWorklogAdapterItem);
    }

    /**
     * Shows information about error. Calls only on failure.
     *
     * @param aThrowable exception.
     */
    private void handleCreateWorklogError(@NonNull Throwable aThrowable){
        Timber.e(aThrowable);
        getViewState().showErrorMessage(aThrowable.getMessage());
    }

}
