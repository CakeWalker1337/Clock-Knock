package com.saritasa.clock_knock.features.tasks.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    public boolean equals(@Nullable final Object aObject){
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

    @Override
    public long getIdentifier(){
        return super.getIdentifier();
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
    @NonNull
    public String getId(){
        return mId;
    }

    /**
     * Sets id of item.
     *
     * @param aId id.
     */
    public void setId(@NonNull final String aId){
        mId = aId;
    }

    /**
     * Gets priority icon url of item.
     *
     * @return priority icon url.
     */
    @NonNull
    public String getPriorityIconUrl(){
        return mPriorityIconUrl;
    }

    /**
     * Sets priority icon url of item.
     *
     * @param aPriorityIconUrl priority icon url.
     */
    public void setPriorityIconUrl(@NonNull final String aPriorityIconUrl){
        mPriorityIconUrl = aPriorityIconUrl;
    }

    /**
     * Gets project avatar url of item.
     *
     * @return project avatar url.
     */
    @NonNull
    public String getProjectAvatarUrl(){
        return mProjectAvatarUrl;
    }

    /**
     * Sets project avatar url of item.
     *
     * @param aProjectAvatarUrl project avatar url.
     */
    public void setProjectAvatarUrl(@NonNull final String aProjectAvatarUrl){
        mProjectAvatarUrl = aProjectAvatarUrl;
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
            Glide.with(ivPriorityIcon).clear(ivPriorityIcon);
            Glide.with(ivProjectIcon).clear(ivProjectIcon);
        }

    }
}
