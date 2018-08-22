package com.saritasa.clock_knock.features.tasks.presentation;

import com.arellomobile.mvp.MvpView;

/**
 * Interface for using presenter's methods in the TasksFragment.
 *
 * @param <VIEW> View to work with. Must be inherited from MvpView.
 */
public interface TasksPresenter<VIEW extends MvpView>{

    /**
     * Attaches view to presenter.
     * @param aView View to attach.
     */
    void attachView(VIEW aView);

    /**
     * Detaches view from presenter.
     * @param aView View to detach.
     */
    void detachView(VIEW aView);

    /**
     * Loads tasks from API and throws it into presenter through ViewState.
     */
    void loadTasks();

}
