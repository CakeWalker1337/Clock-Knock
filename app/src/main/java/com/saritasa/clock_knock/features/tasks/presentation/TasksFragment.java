package com.saritasa.clock_knock.features.tasks.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.base.presentation.BaseFragment;
import com.saritasa.clock_knock.features.main.presentation.NavigationListener;
import com.saritasa.clock_knock.features.tasks.di.TasksModule;
import com.saritasa.clock_knock.util.Strings;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Fragment for tasks module
 */
public class TasksFragment extends BaseFragment implements TasksView{

    NavigationListener mNavigationListener;

    @Inject
    TasksPresenter mTasksPresenter;
    @BindView(R.id.pbTasks)
    ProgressBar mPbLoadingTasks;
    @BindView(R.id.tvNoTasksMessage)
    TextView mTvNoTasksMessage;
    @BindView(R.id.rvTasks)
    RecyclerView mTasksRecyclerView;
    private ItemAdapter<TasksAdapterItem> mItemAdapter;

    @Override
    public void onDestroy(){
        super.onDestroy();
        mTasksPresenter.detachView(this);
    }

    @Override
    public void showTasksView(){
        mTasksRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingProgress(){
        mPbLoadingTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoTasksMessageView(){
        mTvNoTasksMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTasksView(){
        mTasksRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingProgress(){
        mPbLoadingTasks.setVisibility(View.GONE);
    }

    @Override
    public void hideNoTasksMessageView(){
        mTvNoTasksMessage.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(final String aMessage){
        Snackbar.make(getView(), aMessage, Snackbar.LENGTH_LONG);
    }

    @Override
    public void updateTaskList(final List<TasksAdapterItem> aTasksDomains){
        if(aTasksDomains.size() == 0){
            showNoTasksMessageView();
            return;
        }
        showTasksView();
        mItemAdapter.add(aTasksDomains);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null){
            if(getActivity() instanceof NavigationListener){
                mNavigationListener = (NavigationListener) getActivity();
            }
            App.get(getActivity())
                    .getAppComponent()
                    .tasksComponentBuilder()
                    .tasksModule(new TasksModule())
                    .build()
                    .inject(this);

            mTasksPresenter.attachView(this);

            mItemAdapter = new ItemAdapter<>();
            FastAdapter<TasksAdapterItem> adapter = FastAdapter.with(mItemAdapter);

            adapter.withOnClickListener((v, adapter1, item, position) -> {
                mNavigationListener.goToWorklog(item.getName(), Strings.SHOW_TASK_ACTION);
                return false;
            });

            mTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            mTasksRecyclerView.setAdapter(adapter);

            mTasksPresenter.onRequest();
        }

    }

    @Override
    public void onDetach(){
        super.onDetach();
        mNavigationListener = null;
    }
}
