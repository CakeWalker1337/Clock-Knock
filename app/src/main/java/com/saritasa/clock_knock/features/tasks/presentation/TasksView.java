package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;

import com.saritasa.clock_knock.base.presentation.BaseView;

import java.util.List;

/**
 * Interface for using Fragment's methods in presenter.
 */
public interface TasksView extends BaseView{

    /**
     * Shows recycler view with tasks.
     */
    void showTasksView();

    /**
     * Shows ProgressBar in the middle of the screen.
     */
    void showLoadingProgress();

    /**
     * Shows TextView with text means "nothing to show" in the middle of the screen.
     */
    void showNoTasksMessageView();

    /**
     * Hides recycler view with tasks.
     */
    void hideTasksView();

    /**
     * Hides ProgressBar in the middle of the screen.
     */
    void hideLoadingProgress();

    /**
     * Hides TextView with text means "nothing to show" in the middle of the screen.
     */
    void hideNoTasksMessageView();

    /**
     * Shows message through snackbar.
     *
     * @param aMessage message to show.
     */
    void showErrorMessage(@NonNull String aMessage);

    /**
     * Updates data in RecyclerView through replace.
     *
     * @param aTasksDomains New list of data.
     */
    void updateTaskList(@NonNull List<TasksAdapterItem> aTasksDomains);

    /**
     * Sets refreshing value of swipe refresh layout.
     *
     * @param aValue value
     */
    void setTasksRefreshing(boolean aValue);

}
