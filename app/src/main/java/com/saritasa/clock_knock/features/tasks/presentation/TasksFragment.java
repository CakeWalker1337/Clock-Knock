package com.saritasa.clock_knock.features.tasks.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.tasks.di.TasksModule;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Fragment for tasks module
 */
public class TasksFragment extends MvpAppCompatFragment implements TasksView{

    @Inject
    public TasksPresenter mTasksPresenter;

    ItemAdapter<TasksAdapterItem> mItemAdapter;
    ProgressBar mPbLoadingTasks;
    TextView mTvNoTasksMessage;
    RecyclerView mRecyclerView;

    @Override
    public void onDestroy(){
        super.onDestroy();
        mTasksPresenter.detachView(this);
    }

    @Override
    public void showTasksView(){
        mRecyclerView.setVisibility(View.VISIBLE);
        mTvNoTasksMessage.setVisibility(View.GONE);
        mPbLoadingTasks.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingView(){
        mRecyclerView.setVisibility(View.GONE);
        mTvNoTasksMessage.setVisibility(View.GONE);
        mPbLoadingTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoTasksMessageView(){
        mRecyclerView.setVisibility(View.GONE);
        mTvNoTasksMessage.setVisibility(View.VISIBLE);
        mPbLoadingTasks.setVisibility(View.GONE);
    }

    @Override
    public void updateView(final List<TasksAdapterItem> aTasksDomains){
        if(aTasksDomains.size() == 0){
            showNoTasksMessageView();
            return;
        }
        showTasksView();
        mItemAdapter.add(aTasksDomains);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

    }

    @Override
    public void onAttach(@NonNull final Context context){
        super.onAttach(context);
        Timber.d("Attached");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Timber.d("Fragment created");

        App.get(getContext())
                .getAppComponent()
                .tasksComponentBuilder()
                .tasksModule(new TasksModule())
                .build()
                .inject(this);

        mTasksPresenter.attachView(this);

        mRecyclerView = view.findViewById(R.id.rvTasks);
        mItemAdapter = new ItemAdapter<>();
        FastAdapter<TasksAdapterItem> adapter = FastAdapter.with(mItemAdapter);

        adapter.withOnClickListener((v, adapter1, item, position) -> {

            Timber.d("item " + item.getName());
            return false;
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(adapter);

        mPbLoadingTasks = view.findViewById(R.id.pbTasks);
        mTvNoTasksMessage = view.findViewById(R.id.tvNoTasksMessage);

        showLoadingView();
        Timber.d("Loading tasks");
        mTasksPresenter.loadTasks();
    }
}
