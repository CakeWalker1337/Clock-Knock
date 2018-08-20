package com.saritasa.clock_knock.features.tasks.presentation;

import com.arellomobile.mvp.MvpView;

public interface TasksPresenter<VIEW extends MvpView>{

    void attachView(VIEW aView);

    void detachView(VIEW aView);

}
