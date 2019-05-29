package com.saritasa.clock_knock.features.main.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.tasks.presentation.TasksFragment;
import com.saritasa.clock_knock.features.worklog.presentation.WorklogFragment;
import com.saritasa.clock_knock.features.worklog.presentation.service.TimerService;
import com.saritasa.clock_knock.util.Strings;

import javax.inject.Inject;

/**
 * The Main activity class
 */
public class MainActivity extends MvpAppCompatActivity implements MainView, NavigationListener{

    @Inject
    public MainPresenter mMainPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Your tasks");

        App.get(this).getAppComponent()
                .mainComponentBuilder()
                .build()
                .inject(this);

        mMainPresenter.attachView(this);

        long taskId = getIntent().getLongExtra(Strings.TASK_ID_EXTRA, -1);
        String taskKey = getIntent().getStringExtra(Strings.TASK_KEY_EXTRA);
        String action = getIntent().getAction();

        if (mMainPresenter.isTimerActive()) {
            mMainPresenter.onTimerActivityChecked();
        }

        if(taskId != -1 && taskKey != null && action != null){
            goToWorklog(taskId, taskKey, action);
        } else {
            goToTasks();
        }
    }

    public static Intent newIntent(@NonNull Context aContext, long aTaskId, @NonNull String aTaskKey, @NonNull String aAction){
        Intent intent = new Intent(aContext, MainActivity.class);
        intent.setAction(aAction);
        intent.putExtra(Strings.TASK_ID_EXTRA, aTaskId);
        intent.putExtra(Strings.TASK_KEY_EXTRA, aTaskKey);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroy(){
        mMainPresenter.detachView(this);
        super.onDestroy();
    }

    @Override
    public void goToTasks(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new TasksFragment()).addToBackStack(null).commit();
    }

    @Override
    public void goToWorklog(long aTaskId, @NonNull final String aTaskKey, @NonNull final String aAction){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(aTaskKey);
        WorklogFragment worklogFragment = WorklogFragment.newInstance(aTaskId, aTaskKey, aAction);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, worklogFragment).addToBackStack(null).commit();
    }

    @Override
    public void startTimer(long aTaskId, @Nullable String aTaskKey, long aTimestamp){
        Intent intent = TimerService.newIntent(this, Strings.START_SERVICE_ACTION, aTaskId, aTaskKey, aTimestamp);
        startService(intent);
    }

    @Override
    public void runTaskOnUiThread(final Runnable aRunnable){
        runOnUiThread(aRunnable);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        int fragmentsRemain = getSupportFragmentManager().getBackStackEntryCount();
        if(fragmentsRemain == 0){
            finish();
        } else if(fragmentsRemain == 1){
            getSupportActionBar().setTitle("Your tasks");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
