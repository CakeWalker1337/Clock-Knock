package com.saritasa.clock_knock.features.login.presentation;

import com.arellomobile.mvp.MvpView;

/**
 * An interface to providing methods to presenter from View
 *
 * @param <VIEW> View class
 */
public interface LoginPresenter<VIEW extends MvpView>{

    /**
     * Attaches view to presenter
     *
     * @param aView View object
     */
    void attachView(VIEW aView);

    /**
     * Detaches view to presenter
     *
     * @param aView View object
     */
    void detachView(VIEW aView);

    /**
     * Notices presenter when auth button was pressed
     */
    void onLoginClicked();

    /**
     * Attempts presenter to complete the authorization
     */
    void completeAuthorization();

    /**
     * Requests to check access token existence
     *
     * @return true if access token exists, false otherwise
     */
    boolean isAccessTokenExist();
}
