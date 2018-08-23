package com.saritasa.clock_knock.base.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * A class which contains basic methods for presenters, including Moxy implementation
 * @param <VIEW>
 */
public class BasePresenter<VIEW extends BaseView> extends MvpPresenter<VIEW>{

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * Adds Disposable object to container to dispose in when view will be destroyed
     *
     * @param aDisposable Disposable object
     */
    protected void unsubscribeOnDestroy(@Nullable Disposable aDisposable) {
        if (aDisposable != null){
            mCompositeDisposable.add(aDisposable);
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
