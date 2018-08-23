package com.saritasa.clock_knock.features.login.presentation;

import com.saritasa.clock_knock.base.presentation.BaseView;

/**
 * An interface for providing methods from presenter to view
 */
public interface LoginView extends BaseView{

    /**
     * Invokes the screen changing to AuthFragment
     */
    void goToAuthFragment();

    /**
     * Shows error message through snackbar
     *
     * @param aMessage Message string
     */
    void showError(String aMessage);

    /**
     * Notices view when authorization is completed
     */
    void onAuthorizationComplete();
}
