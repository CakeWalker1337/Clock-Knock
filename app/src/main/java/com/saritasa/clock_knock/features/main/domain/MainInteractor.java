package com.saritasa.clock_knock.features.main.domain;

import android.support.annotation.Nullable;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.features.main.presentation.MainPresenter;
import com.saritasa.clock_knock.features.main.presentation.MainPresenterImpl;

/**
 * An interface for providing the interactor methods
 */
public interface MainInteractor extends BaseInteractor{

    /**
     * Checks timer activity
     *
     * @return true if timer is active, false otherwise
     */
    boolean isTimerActive();

    /**
     * Gets start timestamp value
     *
     * @return Start timestamp value
     */
    long getStartTimestamp();

    /**
     * Gets task id string
     *
     * @return Task id string
     */
    @Nullable
    String getTaskId();
}
