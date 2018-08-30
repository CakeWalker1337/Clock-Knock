package com.saritasa.clock_knock.features.worklog.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.base.presentation.BaseFragment;
import com.saritasa.clock_knock.features.main.presentation.NavigationListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class WorklogFragment extends BaseFragment implements WorklogView{

    @Inject
    public WorklogPresenter mWorklogPresenter;

    NavigationListener mNavigationListener;

    boolean isTimerStarted = false;

    String mTaskKey;

    @BindView(R.id.pbTasks)
    ProgressBar mPbLoadingWorklog;
    @BindView(R.id.tvNoLogsMessage)
    TextView mTvNoWorklogMessage;
    @BindView(R.id.rvWorklog)
    RecyclerView mWorklogRecyclerView;

    @BindView(R.id.ibTimerButton)
    ImageButton mTimerButton;

    private ItemAdapter<WorklogAdapterItem> mItemAdapter;

    @Override
    public void onDestroy(){
        super.onDestroy();
        mWorklogPresenter.detachView(this);
    }

    @Override
    public void onAttach(final Context context){
        super.onAttach(context);
        Timber.d("Attached");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_worklog, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getContext() != null){
            if(getContext() instanceof NavigationListener){
                mNavigationListener = (NavigationListener) getContext();
            }

            App.get(getContext())
                    .getAppComponent()
                    .worklogComponentBuilder()
                    .build()
                    .inject(this);

            mWorklogPresenter.attachView(this);

            mItemAdapter = new ItemAdapter<>();
            FastAdapter<WorklogAdapterItem> adapter = FastAdapter.with(mItemAdapter);

            adapter.withOnClickListener((v, adapter1, item, position) -> {
                onWorklogClicked(item);
                return false;
            });

            mTimerButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(final View aView){

                    if(!isTimerStarted){
                        isTimerStarted = true;
                        mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_pause_circle_24dp));
                    } else{
                        isTimerStarted = false;
                        onTimerStop();
                        mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_play_circle_24dp));
                    }
                }
            });

            mWorklogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            mWorklogRecyclerView.setAdapter(adapter);
            onDataRequest(mTaskKey);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Timber.d("Fragment created");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mWorklogPresenter.detachView(this);
    }

    @Override
    public void showWorklogView(){
        mWorklogRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingProgress(){
        mPbLoadingWorklog.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoWorklogMessageView(){
        mTvNoWorklogMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWorklogView(){
        mWorklogRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingProgress(){
        mPbLoadingWorklog.setVisibility(View.GONE);
    }

    @Override
    public void hideNoWorklogMessageView(){
        mTvNoWorklogMessage.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(@NonNull final String aMessage){
        Snackbar.make(getView(), aMessage, Snackbar.LENGTH_LONG);
    }

    @Override
    public void updateWorklogList(@NonNull final List<WorklogAdapterItem> aTasksDomains){
        mItemAdapter.add(aTasksDomains);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();
    }

    @Override
    public void updateActivityTitle(@NonNull final String aNewTitle){
        if(getActivity() == null){
            return;
        }
        ActionBar actionBar = ((MvpAppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar == null){
            return;
        }
        actionBar.setTitle(aNewTitle);
    }

    @Override
    public void addWorklogToList(@NonNull final WorklogAdapterItem aWorklogAdapterItem){
        mItemAdapter.add(0, aWorklogAdapterItem);
    }

    public void setTaskKey(@NonNull String aTaskKey){
        mTaskKey = aTaskKey;
    }

    /**
     * Method calls when timer stop button has clicked.
     */
    public void onTimerStop(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.worklog_dialog, null);
        TimePicker timePicker = view.findViewById(R.id.timePicker);
        EditText description = view.findViewById(R.id.etDescription);
        timePicker.setIs24HourView(true);
        builder.setView(view);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Create",
                (dialog, id) -> {
                    mWorklogPresenter.onTimerStopped(mTaskKey,
                                                     description.getText().toString(),
                                                     timePicker.getHour() * 3600 + timePicker.getMinute() * 60);
                    dialog.cancel();
                });

        builder.setNegativeButton(
                "Cancel",
                (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Method calls when worklog item has been clicked.
     *
     * @param aWorklogAdapterItem worklog item which has been clicked.
     */
    public void onWorklogClicked(@NonNull WorklogAdapterItem aWorklogAdapterItem){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.worklog_dialog, null);
        TimePicker timePicker = view.findViewById(R.id.timePicker);
        EditText descriptionEditText = view.findViewById(R.id.etDescription);
        timePicker.setIs24HourView(true);
        builder.setView(view);
        builder.setCancelable(true);
        int seconds = aWorklogAdapterItem.getTimeSpentSeconds();
        timePicker.setHour(seconds / 3600);
        timePicker.setMinute((seconds % 3600) / 60);
        descriptionEditText.setText(aWorklogAdapterItem.getDescription());
        builder.setPositiveButton(
                "Save",
                (dialog, id) -> {
                    mWorklogPresenter.onWorklogClicked(mTaskKey, aWorklogAdapterItem);
                    dialog.cancel();
                });

        builder.setNegativeButton(
                "Cancel",
                (dialog, id) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onDataRequest(@NonNull String aTaskKey){
        mWorklogPresenter.onDataRequest(aTaskKey);
    }
}
