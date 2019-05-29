package com.saritasa.clock_knock.base.presentation;

import com.arellomobile.mvp.MvpView;

/**
 * An interface for view basic methods
 */
public interface BaseView extends MvpView{

    void runTaskOnUiThread(Runnable aRunnable);
}
