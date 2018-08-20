package com.saritasa.clock_knock.features.tasks.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;

import javax.inject.Inject;

public class TasksFragment extends MvpAppCompatFragment implements TasksView{

    @Inject
    TasksPresenter mTasksPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        App.get(getContext())
                .getAppComponent()
                .tasksComponentBuilder()
                .build()
                .inject(this);

        mTasksPresenter.attachView(this);

        RecyclerView recyclerView = view.findViewById(R.id.rvTasks);
        recyclerView.setAdapter(new TasksAdapter(null));
        //  recyclerView.addOnItemTouchListener();
        //TODO: Set click listener
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mTasksPresenter.detachView(this);
    }

    @Override
    public void showTasks(){

    }
}
