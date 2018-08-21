package com.saritasa.clock_knock.features.tasks.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;

public class TasksFragment extends MvpAppCompatFragment implements TasksView{

    @Inject
    TasksPresenter mTasksPresenter;

    FastAdapter<TasksAdapterItem> mTasksAdapter;

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
        ItemAdapter itemAdapter = new ItemAdapter();
        FastAdapter adapter = FastAdapter.with(itemAdapter);

        recyclerView.setAdapter(adapter);

        mTasksPresenter.loadTasks();
        //  recyclerView.addOnItemTouchListener();
        //TODO: Fix error with build
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mTasksPresenter.detachView(this);
    }

    @Override
    public void showTasks(){

    }

    @Override
    public void updateView(final List<TasksObject> aTasksDomains){

    }

}
