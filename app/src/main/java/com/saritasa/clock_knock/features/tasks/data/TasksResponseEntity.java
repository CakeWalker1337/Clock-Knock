package com.saritasa.clock_knock.features.tasks.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * POJO Class for parsing JSON.
 */
public class TasksResponseEntity{

    @SerializedName("issues")
    private ArrayList<TasksIssueEntity> mIssues;

    public ArrayList<TasksIssueEntity> getIssues(){
        return mIssues;
    }

    public void setIssues(final ArrayList<TasksIssueEntity> aIssues){
        mIssues = aIssues;
    }

}
