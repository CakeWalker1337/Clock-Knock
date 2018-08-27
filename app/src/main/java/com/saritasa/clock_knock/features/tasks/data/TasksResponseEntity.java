package com.saritasa.clock_knock.features.tasks.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * POJO Class for parsing JSON.
 */
public class TasksResponseEntity{

    @SerializedName("issues")
    private ArrayList<TasksIssueEntity> mIssues;

    /**
     * Gets array of issues.
     *
     * @return array of issues
     */
    @NonNull
    public ArrayList<TasksIssueEntity> getIssues(){
        return mIssues;
    }

    /**
     * Sets array of issues.
     * @param aIssues array of issues.
     */
    public void setIssues(@NonNull final ArrayList<TasksIssueEntity> aIssues){
        mIssues = aIssues;
    }

}
