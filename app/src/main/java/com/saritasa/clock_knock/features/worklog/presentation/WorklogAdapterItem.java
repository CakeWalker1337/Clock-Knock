package com.saritasa.clock_knock.features.worklog.presentation;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.saritasa.clock_knock.R;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Data class adapted for RecyclerView's FastAdapter. Data class of presentation layer.
 */
public class WorklogAdapterItem extends AbstractItem<WorklogAdapterItem, WorklogAdapterItem.ViewHolder>{

    private long mId;
    private String mDescription;
    private String mTimeSpent;
    private int mTimeSpentSeconds;
    private Date mCreationDate;

    @NonNull
    @Override
    public ViewHolder getViewHolder(final View aView){
        return new ViewHolder(aView);
    }


    @Override
    public int getType(){
        return R.id.rvWorklog;
    }

    @Override
    public int getLayoutRes(){
        return R.layout.item_worklog;
    }

    /**
     * Gets id of worklog object.
     *
     * @return id of worklog.
     */
    public long getId(){
        return mId;
    }

    /**
     * Gets id of worklog object.
     *
     * @param aId id of worklog.
     */
    public void setId(final long aId){
        mId = aId;
    }

    /**
     * Gets description (comment) of worklog object.
     *
     * @return description (comment) of worklog.
     */
    @NonNull
    public String getDescription(){
        return mDescription;
    }

    /**
     * Gets description (comment) of worklog object.
     *
     * @param aDescription description (comment) of worklog.
     */
    public void setDescription(@NonNull final String aDescription){
        mDescription = aDescription;
    }

    /**
     * Gets time spent on work in the worklog object.
     *
     * @return time spent on work in the worklog object.
     */
    @NonNull
    public String getTimeSpent(){
        return mTimeSpent;
    }

    /**
     * Gets time spent on work in the worklog object.
     *
     * @param aTimeSpent time spent on work in the worklog object.
     */
    public void setTimeSpent(@NonNull final String aTimeSpent){
        mTimeSpent = aTimeSpent;
    }

    /**
     * Gets time spent on work in the worklog object in seconds.
     *
     * @return time spent on work in the worklog object in seconds.
     */
    public int getTimeSpentSeconds(){
        return mTimeSpentSeconds;
    }

    /**
     * Sets time spent on work in the worklog object in seconds.
     *
     * @param aTimeSpentSeconds time spent on work in the worklog object in seconds.
     */
    public void setTimeSpentSeconds(final int aTimeSpentSeconds){
        mTimeSpentSeconds = aTimeSpentSeconds;
    }

    public Date getCreationDate(){
        return mCreationDate;
    }

    public void setCreationDate(final Date aCreationDate){
        mCreationDate = aCreationDate;
    }

    @Override
    public boolean equals(final Object aObject){
        if(this == aObject){
            return true;
        }
        if(!(aObject instanceof WorklogAdapterItem)){
            return false;
        }
        if(!super.equals(aObject)){
            return false;
        }
        WorklogAdapterItem that = (WorklogAdapterItem) aObject;
        return getId() == that.getId() &&
                getTimeSpentSeconds() == that.getTimeSpentSeconds() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getTimeSpent(), that.getTimeSpent());
    }

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), getId(), getDescription(), getTimeSpent(), getTimeSpentSeconds());
    }

    @Override
    public String toString(){
        return "WorklogAdapterItem{" +
                "mId=" + mId +
                ", mDescription='" + mDescription + '\'' +
                ", mTimeSpent='" + mTimeSpent + '\'' +
                ", mTimeSpentSeconds=" + mTimeSpentSeconds +
                '}';
    }


    /**
     * ViewHolder class for FastAdapter
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<WorklogAdapterItem>{

        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvTimeSpent)
        TextView tvTimeSpent;

        public ViewHolder(View aView){
            super(aView);
            ButterKnife.bind(this, aView);
        }

        @Override
        public void bindView(@NonNull final WorklogAdapterItem item, @NonNull final List<Object> payloads){
            tvDescription.setText(item.getDescription());
            tvTimeSpent.setText(item.getTimeSpent());

        }

        @Override
        public void unbindView(@NonNull final WorklogAdapterItem item){
            tvDescription.setText(null);
            tvTimeSpent.setText(null);
        }

    }
}
