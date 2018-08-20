package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.saritasa.clock_knock.features.tasks.data.TasksResponseEntity;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<TasksResponseEntity> mTaskArrayList;

    public TasksAdapter(ArrayList<TasksResponseEntity> aTasksArrayList){
        mTaskArrayList = aTasksArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType){
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position){

    }

    @Override
    public int getItemCount(){
        return (mTaskArrayList == null) ? 0 : mTaskArrayList.size();
    }
}
