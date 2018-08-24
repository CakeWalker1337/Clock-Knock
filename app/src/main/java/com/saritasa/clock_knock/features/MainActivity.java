package com.saritasa.clock_knock.features;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.tasks.presentation.TasksFragment;

import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity{

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.d("Beginning");

        TasksFragment tasksFragment = new TasksFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainLayout, tasksFragment).commit();

    }
}
