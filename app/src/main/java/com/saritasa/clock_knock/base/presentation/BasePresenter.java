package com.saritasa.clock_knock.base.presentation;

import com.arellomobile.mvp.MvpPresenter;

/**
 * Base presenter class.
 *
 * @param <VIEW> View using in presenter. Must be inherit of BaseView.
 */
public class BasePresenter<VIEW extends BaseView> extends MvpPresenter<VIEW>{

    @Override
    public void attachView(final VIEW view){
        super.attachView(view);
    }

    @Override
    public void detachView(final VIEW view){
        super.detachView(view);
    }
}
