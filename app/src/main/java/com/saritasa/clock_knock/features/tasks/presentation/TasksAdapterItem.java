package com.saritasa.clock_knock.features.tasks.presentation;

import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.util.svg.GlideApp;
import com.saritasa.clock_knock.util.svg.SvgSoftwareLayerSetter;

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
    private int mPriority;
    private String mStatus;
    private String mSummary;



    @NonNull
    @Override
    public ViewHolder getViewHolder(final View v){
        Timber.d("Getting viewHolder");
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

            ivProjectIcon.setImageResource(R.mipmap.ic_launcher_round);
            ivPriorityIcon.setImageResource(R.drawable.ic_arrow_upward_black_24dp);

            int priorityColor;
            switch(item.getPriority()) {
                case 0:
                    priorityColor = Color.GREEN;
                    break;
                case 1:
                    priorityColor = Color.rgb(255, 160, 0);
                    break;
                case 2:
                    priorityColor = Color.RED;
                    break;
                default:
                    priorityColor = Color.BLACK;
            }
            ivPriorityIcon.setBackgroundColor(priorityColor);

        }

        @Override
        public void unbindView(@NonNull final TasksAdapterItem item){
            tvTitle.setText(null);
            tvStatus.setText(null);
            tvSummary.setText(null);
            ivPriorityIcon.setImageResource(android.R.color.transparent);
            ivProjectIcon.setImageResource(android.R.color.transparent);
        }

    }
}
