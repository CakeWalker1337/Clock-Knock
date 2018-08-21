package com.saritasa.clock_knock.features.main.domain;

import com.saritasa.clock_knock.base.domain.BaseInteractor;
import com.saritasa.clock_knock.features.main.presentation.MainPresenter;
import com.saritasa.clock_knock.features.main.presentation.MainPresenterImpl;

public interface MainInteractor extends BaseInteractor{

    boolean checkAccessToken();
}
