package com.saritasa.clock_knock.features.tasks.presentation;

import com.saritasa.clock_knock.base.presentation.BaseView;

import java.util.List;

/**
 * Interface for using Fragment's methods in presenter.
 */
public interface TasksView extends BaseView{

    /**
     * Shows recycler view with tasks.
     * Also hides other views on the screen.
     */
    void showTasksView();

    /**
     * Shows ProgressBar in the middle of the screen.
     * Also hides other views on the screen.
     */
    void showLoadingView();

    /**
     * Shows TextView with text means "nothing to show" in the middle of the screen.
     * Also hides other views on the screen.
     */
    void showNoTasksMessageView();

    /**
     * Updates data in RecyclerView through replace.
     *
     * @param aTasksDomains New list of data.
     */
    void updateView(List<TasksAdapterItem> aTasksDomains);
}
