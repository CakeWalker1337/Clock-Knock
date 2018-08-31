package com.saritasa.clock_knock.features.worklog.presentation.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.tasks.presentation.TasksFragment;
import com.saritasa.clock_knock.util.Strings;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A class for implement the service logic
 */
public class TimerService extends Service{

    /**
     * Notification ID
     */
    private static final int NOTIFICATION_ID = 777;

    /**
     * None flags
     */
    private static final int FLAGS_NONE = 0;

    /**
     * One second for timer interval value
     */
    private static final int ONE_SECOND = 1;

    /**
     * Default timestamp
     */
    private static final int DEFAULT_TIMESTAMP = 0;

    /**
     * Request code
     */
    private static final int REQUEST_CODE = 0;

    /**
     * Notification channel ID
     */
    private static final String CHANNEL_ID = "timer_channel";

    /**
     * Time pattern
     */
    private static final String TIME_PATTERN = "HH:mm:ss";

    private Disposable mDisposable;

    private String mTaskId;

    private TimerServiceListener mTimerServiceListener;

    private TimerServiceBinder mTimerServiceBinder = new TimerServiceBinder(){

        @Override
        public void setTimerListener(final TimerServiceListener aListener){
            mTimerServiceListener = aListener;
        }
    };

    public TimerService(){
    }

    @Override
    public IBinder onBind(Intent aIntent){
        return mTimerServiceBinder;
    }

    @Override
    public void onCreate(){
    }

    /**
     * Creates a new notification with a specific time
     *
     * @param aTime Time in seconds
     * @return new notification object
     */
    public Notification createNotification(long aTime){

        Intent notificationIntent = new Intent(this, TasksFragment.class);
        notificationIntent.setAction(Strings.SHOW_ACTIVITY_ACTION);
        notificationIntent.putExtra(Strings.TASK_ID_EXTRA, mTaskId);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                                                                REQUEST_CODE, notificationIntent,
                                                                PendingIntent.FLAG_CANCEL_CURRENT);

        Intent playButtonClicked = new Intent(this, TimerService.class);
        playButtonClicked.setAction(Strings.END_SERVICE_ACTION);
        PendingIntent playButtonIntent = PendingIntent.getService(this, REQUEST_CODE, playButtonClicked, FLAGS_NONE);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.timer_notification_layout);
        contentView.setOnClickPendingIntent(R.id.playButton, playButtonIntent);
        contentView.setTextViewText(R.id.time, longTimeToFormat(aTime));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_pause_circle_outline_black_24dp)
                .setCustomContentView(contentView)
                .setContentIntent(contentIntent);

        return builder.build();
    }

    /**
     * Updates the notification in status bar (replaces with the new notification)
     *
     * @param aTime Time in seconds
     */
    public void updateNotification(long aTime){
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.cancel(NOTIFICATION_ID);
        notificationManagerCompat.notify(NOTIFICATION_ID, createNotification(aTime));
    }

    /**
     * Converts time in milliseconds to format HH:MM:SS
     *
     * @param aTime Time in milliseconds
     * @return Formatted time string
     */
    public String longTimeToFormat(long aTime){
        return DurationFormatUtils.formatDuration(aTime/1000, TIME_PATTERN);
    }

    @Override
    public int onStartCommand(Intent aIntent, int aFlags, int aStartId){

        if(aIntent.getAction() != null){

            switch(aIntent.getAction()){
                case Strings.END_SERVICE_ACTION:
                    Intent intent = new Intent(this, TasksFragment.class);
                    intent.setAction(Strings.STOP_TIMER_ACTION);
                    intent.putExtra(Strings.TIMESTAMP_EXTRA, System.currentTimeMillis());
                    intent.putExtra(Strings.TASK_ID_EXTRA, mTaskId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    onDestroy();
                    break;
                case Strings.START_SERVICE_ACTION:
                    long startTimestamp = aIntent.getLongExtra(Strings.TIMESTAMP_EXTRA, DEFAULT_TIMESTAMP);
                    mTaskId = aIntent.getStringExtra(Strings.TASK_ID_EXTRA);
                    startTimer(startTimestamp);
                    break;
            }
        }
        return START_STICKY;
    }

    /**
     * Starts the one-second repeatable timer
     *
     * @param aStartTimestamp Timestamp to start from
     */
    public void startTimer(long aStartTimestamp){
        Notification notification = createNotification(aStartTimestamp);
        startForeground(NOTIFICATION_ID, notification);

        mDisposable = Observable.interval(ONE_SECOND, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .subscribe(aTime -> {
                    long interval = System.currentTimeMillis() - aStartTimestamp;
                    updateNotification(interval);
                    if(mTimerServiceListener != null){
                        mTimerServiceListener.timerTicked(interval);
                    }
                });
    }

    @Override
    public void onRebind(Intent aIntent){
        super.onRebind(aIntent);
    }

    @Override
    public boolean onUnbind(Intent aIntent){
        mTimerServiceListener = null;
        return super.onUnbind(aIntent);
    }

    @Override
    public void onDestroy(){
        if(mDisposable != null){
            mDisposable.dispose();
        }
        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent aIntent){
    }
}
