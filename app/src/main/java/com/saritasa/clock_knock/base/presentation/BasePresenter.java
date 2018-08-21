package com.saritasa.clock_knock.base.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<VIEW extends BaseView> extends MvpPresenter<VIEW>{

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

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
