package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.utils.GlideRequestOptions;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Data class adapted for RecyclerView's FastAdapter. Data class of presentation layer.
 */
public class TasksAdapterItem extends AbstractItem<TasksAdapterItem, TasksAdapterItem.ViewHolder>{

    private String mName;
    private String mId;
    private String mPriorityIconUrl;
    private String mProjectAvatarUrl;
    private String mStatus;
    private String mSummary;

    public TasksAdapterItem(){
    }

    @Override
    public String toString(){
        return "TasksDomain{" +
                "mName='" + mName + '\'' +
                ", mId='" + mId + '\'' +
                ", mPriorityIconUrl='" + mPriorityIconUrl + '\'' +
                ", mProjectAvatarUrl='" + mProjectAvatarUrl + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mSummary='" + mSummary + '\'' +
                '}';
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(final View v){
        Timber.d("Getting viewHolder");
        return new ViewHolder(v);
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(aObject == null || getClass() != aObject.getClass()){
            return false;
        }
        TasksAdapterItem that = (TasksAdapterItem) aObject;
        return Objects.equals(mName, that.mName) &&
                Objects.equals(mId, that.mId) &&
                Objects.equals(mPriorityIconUrl, that.mPriorityIconUrl) &&
                Objects.equals(mProjectAvatarUrl, that.mProjectAvatarUrl) &&
                Objects.equals(mStatus, that.mStatus) &&
                Objects.equals(mSummary, that.mSummary);
    }

    @Override
    public int hashCode(){

        return Objects.hash(mName, mId, mPriorityIconUrl, mProjectAvatarUrl, mStatus, mSummary);
    }

    @Override
    public int getType(){
        return R.id.rvTasks;
    }

    @Override
    public int getLayoutRes(){
        return R.layout.item_task;
    }

    public String getName(){
        return mName;
    }

    public void setName(final String aName){
        mName = aName;
    }

    public String getId(){
        return mId;
    }

    public void setId(final String aId){
        mId = aId;
    }

    public String getPriorityIconUrl(){
        return mPriorityIconUrl;
    }

    public void setPriorityIconUrl(final String aPriorityIconUrl){
        mPriorityIconUrl = aPriorityIconUrl;
    }

    public String getProjectAvatarUrl(){
        return mProjectAvatarUrl;
    }

    public void setProjectAvatarUrl(final String aProjectAvatarUrl){
        mProjectAvatarUrl = aProjectAvatarUrl;
    }

    public String getStatus(){
        return mStatus;
    }

    public void setStatus(final String aStatus){
        mStatus = aStatus;
    }

    public String getSummary(){
        return mSummary;
    }

    public void setSummary(final String aSummary){
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
                Timber.d("NOT NULL");
            }
            tvTitle.setText(item.getName());
            tvStatus.setText(item.getStatus());
            tvSummary.setText(item.getSummary());

            Glide.with(ivProjectIcon)
                    .setDefaultRequestOptions(GlideRequestOptions.getTasksRequestOptions())
                    .load(item.getProjectAvatarUrl())
                    .into(ivProjectIcon);
            Glide.with(ivPriorityIcon)
                    .setDefaultRequestOptions(GlideRequestOptions.getTasksRequestOptions())
                    .load(item.getPriorityIconUrl())
                    .into(ivPriorityIcon);
        }

        @Override
        public void unbindView(@NonNull final TasksAdapterItem item){
            tvTitle.setText(null);
            tvStatus.setText(null);
            tvSummary.setText(null);
            ivPriorityIcon.setImageBitmap(null);
            ivProjectIcon.setImageBitmap(null);
        }

    }
}
