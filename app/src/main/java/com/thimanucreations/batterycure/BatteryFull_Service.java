package com.thimanucreations.batterycure;  /*

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

public class BatteryFull_Service extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BatteryFull_Service", "onStartCommand");
        // do not receive all available system information (it is a filter!)
        final IntentFilter battChangeFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED);
        // register our receiver
        this.registerReceiver(this.batteryChangeReceiver, battChangeFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    private final BroadcastReceiver batteryChangeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            checkBatteryLevel(intent);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // There are Bound an Unbound Services - you should read something about
        // that. This one is an Unbounded Service.
        return null;
    }

    private void checkBatteryLevel(Intent batteryChangeIntent) {
        // some calculations
        final int currLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_LEVEL, -1);
        final int maxLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_SCALE, -1);
        final int percentage = (int) Math.round((currLevel * 100.0) / maxLevel);

        Log.d("BatteryFull_Service", "current battery level: " + percentage);

        // full battery
        if (percentage == 100) {
            Log.d("BatteryFull_Service", "battery fully loaded");
            Intent intent = new Intent(getBaseContext(), Settings.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        }
        // do not forget to unregister
        unregisterReceiver(batteryChangeReceiver);
    }

}

/*import android.app.PendingIntent;
import android.app.Service;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.content.Intent ;
import android.os.Handler ;
import android.os.IBinder ;
import androidx.core.app.NotificationCompat;
import android.util.Log ;
import java.util.Timer ;
import java.util.TimerTask ;

public class BatteryFull_Service extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    Timer timer ;
    TimerTask timerTask ;
    String TAG = "Timers" ;
    int Your_X_SECS = 5 ;
    @Override
    public IBinder onBind (Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand (Intent intent , int flags , int startId) {
        Log. e ( TAG , "onStartCommand" ) ;
        super .onStartCommand(intent , flags , startId) ;
        startTimer() ;
        return START_STICKY ;
    }
    @Override
    public void onCreate () {
        Log. e ( TAG , "onCreate" ) ;
    }
    @Override
    public void onDestroy () {
        Log. e ( TAG , "onDestroy" ) ;
        stopTimerTask() ;
        super .onDestroy() ;
    }
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler() ;
    public void startTimer () {
        timer = new Timer() ;
        initializeTimerTask() ;
        timer .schedule( timerTask , 5000 , Your_X_SECS * 1000 ) ; //
    }
    public void stopTimerTask () {
        if ( timer != null ) {
            timer .cancel() ;
            timer = null;
        }
    }
    public void initializeTimerTask () {
        timerTask = new TimerTask() {
            public void run () {
                handler .post( new Runnable() {
                    public void run () {
                        createNotification() ;
                    }
                }) ;
            }
        } ;
    }

    /*Intent noti_fullcharged = new Intent(this,Settings.class);
        //noti_fullcharged.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent pendingIntent1 = PendingIntent.getActivity(this,0, noti_fullcharged, PendingIntent.FLAG_UPDATE_CURRENT);

    private void createNotification () {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() , default_notification_channel_id ) ;
        mBuilder.setContentTitle( "BatteryCare Notification (Battery Full)" ) ;
        mBuilder.setContentText( "Battery is Fully Charged. Please Disconnect the charger" ) ;
        mBuilder.setTicker( "Battery Full Charged Notification" ) ;
        mBuilder.setSmallIcon(R.drawable.ic_baseline_battery_full_24 ) ;
        //mBuilder.setContentIntent(pendingIntent1);
        mBuilder.setAutoCancel( true ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
    }
} */


    /*MediaPlayer player;

    @Override
    public IBinder onBind(Intent inte) {
        return null;
    }
    int count = 0;

    public void onCreate() {
        player = MediaPlayer.create(this,R.raw.batteryfull);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                while (count<5) {
                    player.start();
                    count++;
                }
            }
        });

    }

    public int onStartCommand(Intent intent1, int flags, int startId) {
        player.start();
        return Service.START_NOT_STICKY;
    }

    public void onDestroy() {
        player.stop();
        player.release();
        stopSelf();
        super.onDestroy();
    }
}*/
