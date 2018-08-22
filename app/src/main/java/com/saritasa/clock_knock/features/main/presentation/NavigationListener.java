package com.saritasa.clock_knock.features.main.presentation;

public interface NavigationListener{
    void goToLogin();

    void goToAuth();

    void onAuthenticationComplete();

    void goToTasks();
}
