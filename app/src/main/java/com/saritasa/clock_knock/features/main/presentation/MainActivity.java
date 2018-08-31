package com.saritasa.clock_knock.features.main.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.saritasa.clock_knock.App;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.auth.presentation.AuthFragment;
import com.saritasa.clock_knock.features.login.presentation.LoginFragment;
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

        App.get(this).getAppComponent()
                .mainComponentBuilder()
                .build()
                .inject(this);

        mMainPresenter.attachView(this);

        String taskId = getIntent().getStringExtra(Strings.TASK_ID_EXTRA);
        String action = getIntent().getAction();

        Log.w("Activity", "Task: " + taskId + " Action: " + action);

        if (mMainPresenter.isTimerActive()) {
            mMainPresenter.onTimerActivityChecked();
        }

        if (taskId != null && action != null) {
            goToWorklog(taskId, action);
        } else{
            goToLogin();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroy(){
        mMainPresenter.detachView(this);
        super.onDestroy();
    }

    @Override
    public void onAuthenticationComplete(){
        goToLogin();
    }

    @Override
    public void goToLogin(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).commit();
    }

    @Override
    public void goToAuth(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new AuthFragment()).commit();
    }

    @Override
    public void goToTasks(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new TasksFragment()).commit();
    }

    @Override
    public void goToWorklog(@NonNull final String aTaskKey, @NonNull final String aAction){
        WorklogFragment worklogFragment = WorklogFragment.newInstance(aTaskKey, aAction);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, worklogFragment).commit();
    }

    @Override
    public void startTimer(String aTaskId, long aTimestamp){
        Intent intent = new Intent(this, TimerService.class);
        intent.setAction(Strings.START_SERVICE_ACTION);
        intent.putExtra(Strings.TASK_ID_EXTRA, aTaskId);
        intent.putExtra(Strings.TIMESTAMP_EXTRA, aTimestamp);
        startService(intent);
    }
}
