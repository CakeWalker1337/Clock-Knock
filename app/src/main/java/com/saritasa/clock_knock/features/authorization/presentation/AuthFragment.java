package com.saritasa.clock_knock.features.authorization.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.base.presentation.BaseView;

public class AuthFragment extends MvpAppCompatFragment implements BaseView{


    @InjectPresenter
    private AuthPresenter mAuthPresenter;

    public AuthFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onDetach(){
        super.onDetach();

    }

    public void onButtonPressed(Uri uri){

    }
}
