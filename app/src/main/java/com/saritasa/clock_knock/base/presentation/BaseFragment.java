package com.saritasa.clock_knock.base.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends MvpAppCompatFragment{

    protected Unbinder mUnbinder;

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
