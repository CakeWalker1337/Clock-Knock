package com.saritasa.clock_knock.features.worklog.presentation.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.saritasa.clock_knock.R;
import com.saritasa.clock_knock.features.main.presentation.MainActivity;
import com.saritasa.clock_knock.util.DateTimeFormatter;
import com.saritasa.clock_knock.util.Strings;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.saritasa.clock_knock.util.Strings.SHOW_TASK_ACTION;
import static com.saritasa.clock_knock.util.Strings.STOP_TIMER_ACTION;

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

    private Disposable mDisposable;

    private long mTaskId;
    private String mTaskKey;

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

    public static Intent newIntent(@NonNull Context aContext, @NonNull String aAction, long aTaskId, @NonNull String aTaskKey, long aTimestamp){
        Intent intent = new Intent(aContext, TimerService.class);
        intent.setAction(aAction);
        intent.putExtra(Strings.TASK_ID_EXTRA, aTaskId);
        intent.putExtra(Strings.TASK_KEY_EXTRA, aTaskKey);
        intent.putExtra(Strings.TIMESTAMP_EXTRA, aTimestamp);
        return intent;
    }

    /**
     * Creates a new notification with a specific time
     *
     * @param aTime Time in seconds
     * @return new notification object
     */
    public Notification createNotification(long aTime){

        Intent notificationIntent = MainActivity.newIntent(this, mTaskId, mTaskKey, SHOW_TASK_ACTION);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                                                                REQUEST_CODE, notificationIntent,
                                                                PendingIntent.FLAG_CANCEL_CURRENT);

        Intent playButtonClicked = new Intent(this, TimerService.class);
        playButtonClicked.setAction(Strings.END_SERVICE_ACTION);
        PendingIntent playButtonIntent = PendingIntent.getService(this, REQUEST_CODE, playButtonClicked, FLAGS_NONE);

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.timer_notification_layout);
        contentView.setOnClickPendingIntent(R.id.playButton, playButtonIntent);
        contentView.setTextViewText(R.id.time, DateTimeFormatter.longTimeMillisToFormat(aTime, Strings.TIME_PATTERN));
        contentView.setTextViewText(R.id.task, mTaskKey);

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

    @Override
    public int onStartCommand(Intent aIntent, int aFlags, int aStartId){

        String action = aIntent.getAction();
        if(action != null){
            if(action.equals(Strings.END_SERVICE_ACTION)){

                Intent intent = MainActivity.newIntent(this, mTaskId, mTaskKey, STOP_TIMER_ACTION);
                startActivity(intent);
                onDestroy();

            } else if(action.equals(Strings.START_SERVICE_ACTION)){

                if(mDisposable == null || mDisposable.isDisposed()){
                    long startTimestamp = aIntent.getLongExtra(Strings.TIMESTAMP_EXTRA, DEFAULT_TIMESTAMP);
                    mTaskId = aIntent.getLongExtra(Strings.TASK_ID_EXTRA, -1);
                    mTaskKey = aIntent.getStringExtra(Strings.TASK_KEY_EXTRA);
                    startTimer(startTimestamp);
                }
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
                }, Timber::e);
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
