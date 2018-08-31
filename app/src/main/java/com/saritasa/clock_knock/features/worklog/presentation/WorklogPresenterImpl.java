package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.saritasa.clock_knock.base.presentation.BasePresenterImpl;
import com.saritasa.clock_knock.features.worklog.domain.WorklogInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
        getViewState().hideWorklogView();
        getViewState().hideNoWorklogMessageView();
        Disposable disposable = mWorklogInteractor.loadWorklog(aTaskKey)
                .map(WorklogMapper::mapWorklogDomainToWorklogAdapterItem)
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    Timber.d("finally");
                    getViewState().setTasksRefreshing(false);
                    getViewState().hideLoadingProgress();
                })
                .subscribe(this::handleLoadWorklogsSuccess,
                           this::handleLoadWorklogsError);
        unsubscribeOnDestroy(disposable);
    }

    @Override
    public void onTimerStopped(@NonNull String aTaskKey, @NonNull String aDescription, int aTimeSpentSeconds){
        getViewState().showLoadingProgress();
        getViewState().hideWorklogView();
        getViewState().hideNoWorklogMessageView();
        WorklogAdapterItem adapterItem = new WorklogAdapterItem();
        adapterItem.setDescription(aDescription);
        adapterItem.setTimeSpentSeconds(aTimeSpentSeconds);
        Disposable disposable = mWorklogInteractor.createWorklog(aTaskKey, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(adapterItem))
                .map(WorklogMapper::mapWorklogDomainToWorklogAdapterItem)
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    Timber.d("finally");
                    getViewState().hideLoadingProgress();
                })
                .subscribe(this::handleCreateWorklogSuccess, this::handleCreateWorklogError, () -> {
                    throw new Exception("Worklog fetching error.");
                });
        unsubscribeOnDestroy(disposable);
    }

    @Override
    public void onWorklogClicked(@NonNull String aTaskKey, @NonNull WorklogAdapterItem aWorklogAdapterItem){
        mWorklogInteractor.saveWorklog(aTaskKey, WorklogMapper.mapWorklogDomainFromWorklogAdapterItem(aWorklogAdapterItem));
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
