package com.saritasa.clock_knock.features.main.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;

/**
 * An interface to providing methods to presenter from View
 *
 * @param <VIEW> View class
 */
public interface MainPresenter<VIEW extends MvpView>{

    /**
     * Attaches view to presenter
     *
     * @param aView View object
     */
    void attachView(@NonNull VIEW aView);

    /**
     * Detaches view to presenter
     *
     * @param aView View object
     */
    void detachView(@NonNull VIEW aView);

    /**
     * Checks timer activity
     *
     * @return true if timer is active, false otherwise
     */
    boolean isTimerActive();

    /**
     * Calls when timer is checked on existence
     */
    void onTimerActivityChecked();
}
