package com.saritasa.clock_knock.features.main.presentation;

import android.support.annotation.NonNull;

/**
 * An interface to providing methods from fragments to MainActivity
 */
public interface NavigationListener{

    /**
     * Changes fragment to LoginFragment
     */
    void goToLogin();

    /**
     * Changes fragment to AuthFragment
     */
    void goToAuth();

    /**
     * Notices activity when authentication ends
     */
    void onAuthenticationComplete();

    /**
     * Changes fragment to TaskFragment
     */
    void goToTasks();

    void goToWorklog(@NonNull String aTaskKey);
}
