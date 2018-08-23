package com.saritasa.clock_knock.features.authorization.presentation;


import com.saritasa.clock_knock.base.presentation.BaseView;

/**
 * An interface for providing methods from presenter to view
 */
public interface AuthView extends BaseView{

    /**
     * Requests to load page into WebView by URL
     *
     * @param url
     */
    void loadPageByUrl(String url);

    /**
     * Invokes the screen changing to LoginFragment
     */
    void goToLogin();

    /**
     * Calls when authentication is about to complete
     */
    void completeAuthentication();

    /**
     * Shows error message through snackbar
     *
     * @param aMessage Message string
     */
    void showError(String aMessage);
}
