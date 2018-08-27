package com.saritasa.clock_knock.base.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base presenter class.
 *
 * @param <VIEW> View using in presenter. Must be inherit of BaseView.
 */
public class BasePresenterImpl<VIEW extends BaseView> extends MvpPresenter<VIEW> implements BasePresenter<VIEW>{

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(@Nullable final VIEW aView){
        super.attachView(aView);
    }

    @Override
    public void detachView(@NonNull final VIEW aView){
        super.detachView(aView);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    /**
     * Adds Disposable object to container to dispose in when view will be destroyed
     *
     * @param aDisposable Disposable object
     */
    protected void unsubscribeOnDestroy(@Nullable Disposable aDisposable){
        if(aDisposable != null){
            mCompositeDisposable.add(aDisposable);
        }
    }
}
