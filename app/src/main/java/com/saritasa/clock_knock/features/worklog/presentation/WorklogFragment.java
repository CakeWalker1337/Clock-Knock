package com.saritasa.clock_knock.features.worklog.presentation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.saritasa.clock_knock.features.worklog.di.WorklogModule;
import com.saritasa.clock_knock.features.worklog.presentation.service.TimerService;
import com.saritasa.clock_knock.features.worklog.presentation.service.TimerServiceBinder;
import com.saritasa.clock_knock.util.Constants;
import com.saritasa.clock_knock.util.Strings;

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

    private Intent mServiceIntent;

    private ServiceConnection mServiceConnection;

    private TimerServiceBinder mTimerServiceBinder;

    @BindView(R.id.pbTasks)
    ProgressBar mPbLoadingWorklog;
    @BindView(R.id.tvNoLogsMessage)
    TextView mTvNoWorklogMessage;
    @BindView(R.id.rvWorklog)
    RecyclerView mWorklogRecyclerView;
    @BindView(R.id.ibTimerButton)
    ImageButton mTimerButton;
    @BindView(R.id.tvTime)
    TextView mTimeTextView;

    private ItemAdapter<WorklogAdapterItem> mItemAdapter;

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mWorklogPresenter.isTimerActive()){
            unbindService();
        }
        mWorklogPresenter.detachView(this);
    }

    @Override
    public void onAttach(final Context aContext){
        super.onAttach(aContext);
        Timber.d("Attached");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle aSavedInstanceState){
        return aInflater.inflate(R.layout.fragment_worklog, aContainer, false);
    }

    public static WorklogFragment newInstance(@Nullable String aTaskId, @Nullable String aAction){

        Bundle args = new Bundle();
        args.putString(Strings.TASK_ID_EXTRA, aTaskId);
        args.putString(Strings.ACTION_EXTRA, aAction);

        WorklogFragment worklogFragmentatFragment = new WorklogFragment();
        worklogFragmentatFragment.setArguments(args);
        return worklogFragmentatFragment;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle aSavedInstanceState){
        super.onActivityCreated(aSavedInstanceState);

        if(requireActivity() instanceof NavigationListener){
            mNavigationListener = (NavigationListener) getActivity();
        }

        App.get(requireActivity())
                .getAppComponent()
                .worklogComponentBuilder()
                .worklogModule(new WorklogModule())
                .build()
                .inject(this);

        mWorklogPresenter.attachView(this);

        String action = getExtraValues();
        mWorklogPresenter.onActionGot(action);

        mItemAdapter = new ItemAdapter<>();
        FastAdapter<WorklogAdapterItem> adapter = FastAdapter.with(mItemAdapter);

        adapter.withOnClickListener((aView1, aAdapter, aItem, aPosition) -> {
            onWorklogClicked(aItem);
            return false;
        });

        initializeServiceConnection();

        if(mWorklogPresenter.isTimerActive() && mWorklogPresenter.getTimerTask().equals(mTaskKey)){
            bindService();
            mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_pause_circle_24dp));
            isTimerStarted = true;
        }

        mTimerButton.setOnClickListener(aView -> {

            if(!isTimerStarted){
                mWorklogPresenter.onStartButtonClicked(mTaskKey);
            } else{
                mWorklogPresenter.onStopButtonClicked();
            }
        });

        mWorklogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mWorklogRecyclerView.setAdapter(adapter);
        onDataRequest(mTaskKey);
    }

    /**
     * Inits service connection
     */
    private void initializeServiceConnection(){
        mServiceConnection = new ServiceConnection(){

            @Override
            public void onServiceConnected(final ComponentName aComponentName, final IBinder aIBinder){
                mTimerServiceBinder = (TimerServiceBinder) aIBinder;
                mTimerServiceBinder.setTimerListener(aTime -> {
                    mWorklogPresenter.onTimerTicked(aTime);
                });
            }

            @Override
            public void onServiceDisconnected(final ComponentName aComponentName){
            }
        };
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mNavigationListener = null;
    }

    @Override
    public void onViewCreated(View aView, @Nullable Bundle aSavedInstanceState){
        super.onViewCreated(aView, aSavedInstanceState);

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

    @Override
    public void setTimeToTimer(final String aTime){

        getActivity().runOnUiThread(() -> mTimeTextView.setText(aTime));

    }

    @Override
    public void startTimer(final long aTimestamp){

        if(mWorklogPresenter.isTimerActive()){
            stopService();
        }

        startService(mTaskKey, aTimestamp);
        bindService();
        isTimerStarted = true;
        mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_pause_circle_24dp));
    }

    @Override
    public void tryToStopTimer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_worklog, null);
        TimePicker timePicker = view.findViewById(R.id.timePicker);

        timePicker.setHour(mWorklogPresenter.getHours());
        timePicker.setMinute(mWorklogPresenter.getMinutes());

        EditText description = view.findViewById(R.id.etDescription);
        timePicker.setIs24HourView(true);
        builder.setView(view);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Create",
                (dialog, id) -> {
                    mWorklogPresenter.onTimerStopped(mTaskKey,
                                                     description.getText().toString(),
                                                     timePicker.getHour() * Constants.ONE_HOUR_SEC + timePicker.getMinute() * Constants.ONE_MINUTE_SEC);
                    isTimerStarted = false;
                    mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_play_circle_24dp));
                    setTimeToTimer(getString(R.string.default_time_string));
                    stopService();
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
        View view = layoutInflater.inflate(R.layout.dialog_worklog, null);
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

    /**
     * On data request
     *
     * @param aTaskKey Task id string
     */
    public void onDataRequest(@NonNull String aTaskKey){
        mWorklogPresenter.onDataRequest(aTaskKey);
    }

    /**
     * Starts service
     *
     * @param aTaskKey Task id string
     * @param aTimestamp Timestamp value
     */
    public void startService(String aTaskKey, long aTimestamp){
        mServiceIntent = TimerService.newIntent(getContext(), Strings.START_SERVICE_ACTION, aTaskKey, aTimestamp);
        getActivity().startService(mServiceIntent);
    }

    /**
     * Stops service
     */
    public void stopService(){
        if(mServiceIntent == null){
            mServiceIntent = new Intent(getActivity(), TimerService.class);
        }
        getActivity().stopService(mServiceIntent);
    }

    /**
     * Binds fragment to service
     */
    public void bindService(){
        if(mServiceIntent == null){
            mServiceIntent = new Intent(getActivity(), TimerService.class);
        }
        Log.w("Test", "Service is null: " + (mServiceIntent == null));
        getActivity().bindService(mServiceIntent, mServiceConnection, 0);
    }

    /**
     * Unbinds service
     */
    public void unbindService(){
        getActivity().unbindService(mServiceConnection);
    }

    /**
     * Gets values from extra
     *
     * @return Action string
     */
    @Nullable
    public String getExtraValues(){
        mTaskKey = getArguments().getString(Strings.TASK_ID_EXTRA);
        String action = getArguments().getString(Strings.ACTION_EXTRA);
        return action;
    }
}
