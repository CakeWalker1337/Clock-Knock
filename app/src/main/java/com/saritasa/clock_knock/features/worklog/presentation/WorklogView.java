package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.presentation.BaseView;

import java.util.List;

public interface WorklogView extends BaseView{

    /**
     * Shows recycler view with Worklog.
     */
    void showWorklogView();

    /**
     * Shows ProgressBar in the middle of the screen.
     */
    void showLoadingProgress();

    /**
     * Shows TextView with text means "nothing to show" in the middle of the screen.
     */
    void showNoWorklogMessageView();

    /**
     * Hides recycler view with Worklog.
     */
    void hideWorklogView();

    /**
     * Hides ProgressBar in the middle of the screen.
     */
    void hideLoadingProgress();

    /**
     * Hides TextView with text means "nothing to show" in the middle of the screen.
     */
    void hideNoWorklogMessageView();

    /**
     * Shows message through snackbar.
     *
     * @param aMessage message to show.
     */
    void showErrorMessage(@NonNull String aMessage);

    /**
     * Updates worklog list into the item adapter.
     *
     * @param aWorklogDomains new list of data.
     */
    void updateWorklogList(@NonNull List<WorklogAdapterItem> aWorklogDomains);

    /**
     * Updates activity title.
     *
     * @param aNewTitle new activity title.
     */
    void updateActivityTitle(@NonNull String aNewTitle);

    /**
     * Adds worklog adapter item to exist list of data inside item adapter.
     *
     * @param aWorklogAdapterItem worklog adapter item object.
     */
    void addWorklogToList(@NonNull WorklogAdapterItem aWorklogAdapterItem);

    /**
     * Sets refreshing value of swipe refresh layout.
     *
     * @param aValue value
     */
    void setTasksRefreshing(boolean aValue);
}
