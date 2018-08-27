package com.saritasa.clock_knock.features.authorization.presentation;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;

/**
 * An interface to providing methods to presenter from View
 *
 * @param <VIEW> View class
 */
public interface AuthPresenter<VIEW extends MvpView>{

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
     * Called when button "Allowed" was clicked on the OAuth page
     *
     * @param aPage Page body string
     */
    void onAuthAllowed(@NonNull String aPage);

    /**
     * Called when button "Deny" was clicked on the OAuth page
     */
    void onAuthDenied();

    /**
     * Requests the OAuth page to WebView
     */
    void getAuthPage();
}
