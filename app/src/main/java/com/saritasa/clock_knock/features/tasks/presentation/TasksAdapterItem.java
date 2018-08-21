package com.saritasa.clock_knock.features.tasks.presentation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.saritasa.clock_knock.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksAdapterItem extends AbstractItem<TasksAdapterItem, TasksAdapterItem.ViewHolder>{

    private TasksObject mTasksObject;

    public TasksAdapterItem(TasksObject aTasksObject){
        mTasksObject = aTasksObject;
    }

    @Override
    public ViewHolder getViewHolder(final View v){
        return new ViewHolder(v);
    }

    @Override
    public int getType(){
        return R.string.tasks_adapter_name;
    }

    @Override
    public int getLayoutRes(){
        return R.layout.item_task;
    }

    public TasksObject getTasksObject(){
        return mTasksObject;
    }

    protected static class ViewHolder extends FastAdapter.ViewHolder<TasksAdapterItem>{

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.tvSummary)
        TextView tvSummary;
        @BindView(R.id.ivPriorityIcon)
        ImageView ivPriorityIcon;
        @BindView(R.id.ivProjectIcon)
        ImageView ivProjectIcon;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(final TasksAdapterItem item, final List<Object> payloads){
            tvTitle.setText(item.getTasksObject().getKey());
            tvStatus.setText(item.getTasksObject().getKey());
            tvSummary.setText(item.getTasksObject().getKey());
            Glide.with(ivPriorityIcon).load(item.getTasksObject().getPriorityIconUrl()).into(ivPriorityIcon);
            Glide.with(ivProjectIcon).load(item.getTasksObject().getProjectAvatarUrl()).into(ivProjectIcon);
        }

        @Override
        public void unbindView(final TasksAdapterItem item){
            tvTitle.setText(null);
            tvStatus.setText(null);
            tvSummary.setText(null);
            ivPriorityIcon.setImageBitmap(null);
            ivProjectIcon.setImageBitmap(null);
        }

    }
}
