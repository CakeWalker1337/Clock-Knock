package com.saritasa.clock_knock.base.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;

/**
 * An interface to providing methods to presenter from View
 *
 * @param <VIEW> View class
 */
public interface BasePresenter<VIEW extends MvpView>{

    /**
     * Attaches view to presenter.
     *
     * @param aView View to attach.
     */
    void attachView(@NonNull final VIEW aView);

    /**
     * Detaches view from presenter.
     *
     * @param aView View to detach.
     */
    void detachView(@NonNull final VIEW aView);

}
