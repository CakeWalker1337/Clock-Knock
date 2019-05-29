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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
    long mTaskId;

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
    @BindView(R.id.tvTotalTime)
    TextView mTotalTimeTextView;

    private WorklogAdapterItem mChosenItem;

    private int mChosenPosition;

    private ItemAdapter<WorklogAdapterItem> mItemAdapter;

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mWorklogPresenter.isTimerActive() && mWorklogPresenter.getTimerTask() == mTaskId){
            unbindService();
        }
        mWorklogPresenter.detachView(this);
    }

    @Override
    public void onAttach(final Context aContext){
        super.onAttach(aContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle aSavedInstanceState){
        return aInflater.inflate(R.layout.fragment_worklog, aContainer, false);
    }

    public static WorklogFragment newInstance(long aTaskId, @Nullable String aTaskKey, @Nullable String aAction){
        Bundle args = new Bundle();
        args.putLong(Strings.TASK_ID_EXTRA, aTaskId);
        args.putString(Strings.TASK_KEY_EXTRA, aTaskKey);
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

        adapter.withOnLongClickListener((aView1, aAdapter, aItem, aPosition) -> {
            mChosenItem = aItem;
            mChosenPosition = aPosition;
            return false;
        });

        initializeServiceConnection();

        if(mWorklogPresenter.isTimerActive() && mWorklogPresenter.getTimerTask() == mTaskId){
            bindService();
            mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_check_circle_black_24dp));
            isTimerStarted = true;
        }

        mTimerButton.setOnClickListener(aView -> {

            if(!isTimerStarted){
                mWorklogPresenter.onStartButtonClicked(mTaskId, mTaskKey);
            } else{
                mWorklogPresenter.onStopButtonClicked();
            }
        });

        mWorklogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerForContextMenu(mWorklogRecyclerView);
        mWorklogRecyclerView.setAdapter(adapter);
        onDataRequest(mTaskId, mTaskKey);
    }

    /**
     * Inits service connection
     */
    private void initializeServiceConnection(){
        mServiceConnection = new ServiceConnection(){

            @Override
            public void onServiceConnected(final ComponentName aComponentName, final IBinder aIBinder){
                mTimerServiceBinder = (TimerServiceBinder) aIBinder;
                mTimerServiceBinder.setTimerListener(aTime -> mWorklogPresenter.onTimerTicked(aTime));
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
        Toast.makeText(getContext(), aMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setWorklogList(@NonNull final List<WorklogAdapterItem> aTasksDomains){
        mItemAdapter.clear();
        mItemAdapter.add(aTasksDomains);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

        if(mItemAdapter.getAdapterItemCount() > 0){
            hideNoWorklogMessageView();
            showWorklogView();
        } else{
            hideWorklogView();
            showNoWorklogMessageView();
        }
    }

    @Override
    public void addWorklogToList(@NonNull final WorklogAdapterItem aWorklogAdapterItem){
        mItemAdapter.add(0, aWorklogAdapterItem);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

        if(mItemAdapter.getAdapterItemCount() > 0){
            hideNoWorklogMessageView();
            showWorklogView();
        }
    }

    @Override
    public void editWorklogInList(final int aPosition, @NonNull final WorklogAdapterItem aWorklogAdapterItem){
        mItemAdapter.set(aPosition, aWorklogAdapterItem);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();
    }

    @Override
    public void removeWorklogFromList(final int aPosition){
        mItemAdapter.remove(aPosition);
        mItemAdapter.getFastAdapter().notifyAdapterDataSetChanged();

        if(mItemAdapter.getAdapterItemCount() == 0){
            hideWorklogView();
            showNoWorklogMessageView();
        }
    }

    @Override
    public void setTimeToTimer(final String aTime){
        if(getActivity() != null){
            getActivity().runOnUiThread(() -> mTimeTextView.setText(aTime));
        }
    }

    @Override
    public void startTimer(final long aTimestamp){

        if(mWorklogPresenter.isTimerActive()){
            stopService();
        }

        startService(mTaskKey, aTimestamp);
        bindService();
        isTimerStarted = true;
        mTimerButton.setBackground(getActivity().getDrawable(R.drawable.ic_check_circle_black_24dp));
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
                    mWorklogPresenter.onWorklogAdd(mTaskId,
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

    @Override
    public void showTotalTimeString(String aTotalTime){
        mTotalTimeTextView.setText(aTotalTime);
    }

    /**
     * Method calls when worklog item has been clicked.
     */
    public void showEditWorklogAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_worklog, null);
        TimePicker timePicker = view.findViewById(R.id.timePicker);
        EditText descriptionEditText = view.findViewById(R.id.etDescription);
        timePicker.setIs24HourView(true);
        builder.setView(view);
        builder.setCancelable(true);
        int seconds = mChosenItem.getTimeSpentSeconds();
        timePicker.setHour(seconds / 3600);
        timePicker.setMinute((seconds % 3600) / 60);
        descriptionEditText.setText(mChosenItem.getDescription());
        builder.setPositiveButton(
                "Save",
                (dialog, id) -> {
                    mWorklogPresenter.onWorklogEdit(mChosenPosition,
                                                    mChosenItem,
                                                    mTaskId,
                                                    descriptionEditText.getText().toString(),
                                                    timePicker.getHour() * Constants.ONE_HOUR_SEC + timePicker.getMinute() * Constants.ONE_MINUTE_SEC);

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
     * @param aTaskId Task id string
     */
    public void onDataRequest(long aTaskId, String aTaskKey){
        mWorklogPresenter.onDataRequest(aTaskId, aTaskKey);
    }

    /**
     * Starts service
     *
     * @param aTaskKey Task id string
     * @param aTimestamp Timestamp value
     */
    public void startService(String aTaskKey, long aTimestamp){
        mServiceIntent = TimerService.newIntent(getContext(), Strings.START_SERVICE_ACTION, mTaskId, aTaskKey, aTimestamp);
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
        mTaskId = getArguments().getLong(Strings.TASK_ID_EXTRA);
        mTaskKey = getArguments().getString(Strings.TASK_KEY_EXTRA);
        String action = getArguments().getString(Strings.ACTION_EXTRA);
        return action;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.edit:
                showEditWorklogAlertDialog();
                break;
            case R.id.delete:
                mWorklogPresenter.onWorklogDelete(mChosenPosition, mTaskId, mChosenItem);
                break;

            default:
                mChosenItem = null;
                mChosenPosition = -1;
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public void runTaskOnUiThread(final Runnable aRunnable){
        if(getActivity() != null){
            getActivity().runOnUiThread(aRunnable);
        }
    }
}
