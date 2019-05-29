package com.saritasa.clock_knock.features.tasks.presentation;

import com.arellomobile.mvp.MvpView;
import com.saritasa.clock_knock.base.presentation.BasePresenter;

/**
 * Interface for using presenter's methods in the TasksFragment.
 *
 * @param <VIEW> View to work with. Must be inherited from MvpView.
 */
public interface TasksPresenter<VIEW extends MvpView> extends BasePresenter<VIEW>{

    /**
     * Loads tasks from API and throws it into presenter through ViewState.
     */
    void onRequest();

    void onTaskAdd(String aTitle, String aSummary, String aStatus, int aPriority);

    void onTaskEdit(int aPosition, long aId, String aTitle, String aSummary, String aStatus, int aPriority);

    void onTaskDelete(int aPostition, TasksAdapterItem aTasksAdapterItem);

}
