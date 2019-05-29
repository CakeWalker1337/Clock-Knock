package com.saritasa.clock_knock.features.tasks.presentation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Data class adapted for RecyclerView's FastAdapter. Data class of presentation layer.
 */
public class TasksAdapterItem extends AbstractItem<TasksAdapterItem, TasksAdapterItem.ViewHolder>{

    private String mName;
    private long mId;
    private int mPriority;
    private String mStatus;
    private String mSummary;

    @NonNull
    @Override
    public ViewHolder getViewHolder(final View v){
        return new ViewHolder(v);
    }

    @Override
    public int getType(){
        return R.id.rvTasks;
    }

    @Override
    public int getLayoutRes(){
        return R.layout.item_task;
    }

    @Override
    public long getIdentifier(){
        return super.getIdentifier();
    }

    /**
     * Gets the priority value
     *
     * @return Priority value
     */
    public int getPriority(){
        return mPriority;
    }

    /**
     * Sets the priority value
     *
     * @param aPriority Priority value
     */
    public void setPriority(final int aPriority){
        mPriority = aPriority;
    }

    /**
     * Gets name of item.
     *
     * @return name.
     */
    @NonNull
    public String getName(){
        return mName;
    }

    /**
     * Sets name of item.
     *
     * @param aName name.
     */
    public void setName(@NonNull final String aName){
        mName = aName;
    }

    /**
     * Gets id of item.
     *
     * @return id.
     */
    public long getId(){
        return mId;
    }

    /**
     * Sets id of item.
     *
     * @param aId id.
     */
    public void setId(final long aId){
        mId = aId;
    }

    /**
     * Gets status of item.
     *
     * @return status.
     */
    @NonNull
    public String getStatus(){
        return mStatus;
    }

    /**
     * Sets status of item.
     *
     * @param aStatus name.
     */
    public void setStatus(@NonNull final String aStatus){
        mStatus = aStatus;
    }

    /**
     * Gets summary of item.
     *
     * @return summary.
     */
    @NonNull
    public String getSummary(){
        return mSummary;
    }

    /**
     * Sets summary of item.
     *
     * @param aSummary summary.
     */
    public void setSummary(@NonNull final String aSummary){
        mSummary = aSummary;
    }

    /**
     * ViewHolder class for FastAdapter
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<TasksAdapterItem>{

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvSummary)
        TextView tvSummary;
        @BindView(R.id.tvPriorityText)
        TextView tvPriorityText;
        @BindView(R.id.ivPriorityIcon)
        ImageView ivPriorityIcon;
        @BindView(R.id.ivProjectIcon)
        ImageView ivProjectIcon;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(@NonNull final TasksAdapterItem item, @NonNull final List<Object> payloads){
            if(tvTitle != null){
            }
            tvTitle.setText(item.getName());
            tvSummary.setText(item.getSummary());

            ivPriorityIcon.setImageResource(R.drawable.ic_arrow_upward_black_24dp);

            Context context = tvPriorityText.getContext();
            String[] priorities = context.getResources().getStringArray(R.array.task_priority);
            tvPriorityText.setText(priorities[item.getPriority()]);

            int priorityColor = Color.BLACK;
            if(item.getPriority() != Constants.PRIORITY_INVALID){
                int[] priorityColors = context.getResources().getIntArray(R.array.task_priority_colors);
                priorityColor = priorityColors[item.getPriority()];
            }
            ivPriorityIcon.setColorFilter(priorityColor);

            TypedArray statusColors = context.getResources().obtainTypedArray(R.array.task_status_colors);
            List<String> statuses = Arrays.asList(context.getResources().getStringArray(R.array.task_status));
            int statusDrawable = R.drawable.ic_task_status_inprogress_background;
            int index = statuses.indexOf(item.getStatus());
            if(index != -1){
                statusDrawable = statusColors.getResourceId(index, R.drawable.ic_task_status_inprogress_background);
            }
            tvStatus.setText(item.getStatus());
            tvStatus.setBackground(context.getDrawable(statusDrawable));
            statusColors.recycle();

            TypedArray projectIcons = context.getResources().obtainTypedArray(R.array.project_icons);
            ivProjectIcon.setImageResource(projectIcons.getResourceId(Integer.valueOf(String.valueOf(item.getId() % 10)), 0));
            projectIcons.recycle();
        }

        @Override
        public void unbindView(@NonNull final TasksAdapterItem item){
            tvTitle.setText(null);
            tvStatus.setText(null);
            tvSummary.setText(null);
            tvPriorityText.setText(null);
            ivPriorityIcon.setImageResource(android.R.color.transparent);
            ivProjectIcon.setImageResource(android.R.color.transparent);
        }

    }
}
