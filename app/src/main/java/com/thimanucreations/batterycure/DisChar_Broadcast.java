package com.thimanucreations.batterycure; /*

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.BatteryManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;




public class DisChar_Broadcast extends BroadcastReceiver {

   // IntentFilter intentfilter3;
    //BroadcastReceiver disCharge;
    //  int deviceStatus;
    @Override
    public void onReceive(Context context4, Intent intent4) {


       // intentfilter3 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


    BroadcastReceiver disCharge = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context3, Intent intent3) {


            Integer integerBatteryLevel = intent3.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            if ((integerBatteryLevel >= 90) && (integerBatteryLevel < 100)) {


         /*   intentfilter3 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


            deviceStatus = intent3.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int level = intent3.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent3.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel = (int) (((float) level / (float) scale) * 100.0f);


            if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                if (batteryLevel >= 90) {

                Intent notifDischar = new Intent(context3, Settings.class);
                notifDischar.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent3 =
                        PendingIntent.getActivity(context3, 0, notifDischar, PendingIntent.FLAG_UPDATE_CURRENT);

                Uri discharSound =
                        Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context3.getPackageName() + "/" + R.raw.discharging);

                NotificationCompat.Builder builder3 =
                        new NotificationCompat.Builder(context3, "notifyBatteryCare")
                                .setSmallIcon(R.drawable.ic_baseline_battery_full_24)
                                .setContentTitle("BatteryCare Notification")
                                .setContentText("Battery is Discharging. Please Connect the charger")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setContentIntent(pendingIntent3)
                                .setAutoCancel(true);

                NotificationManager NotificationManager3 =
                        (NotificationManager) context3.getSystemService(Context.NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    AudioAttributes audioAttributes3 = new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build();

                    int importance3 = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel3 =
                            new NotificationChannel("2824", "NOTIFICATION_CHANNEL_NAME", importance3);
                    notificationChannel3.enableLights(true);
                    notificationChannel3.setLightColor(Color.RED);
                    notificationChannel3.enableVibration(true);
                    notificationChannel3.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    notificationChannel3.setSound(discharSound, audioAttributes3);
                    builder3.setChannelId("2824");

                    assert NotificationManager3 != null;
                    NotificationManager3.createNotificationChannel(notificationChannel3);
                }
                assert NotificationManager3 != null;
                NotificationManager3.notify((int) System.currentTimeMillis(),
                        builder3.build());


                NotificationManagerCompat notificationManager3 =
                        NotificationManagerCompat.from(context3);
                notificationManager3.notify(100, builder3.build());


                }
            }

        //   }
        };
        context4.registerReceiver(disCharge, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }
}

   // ( Manifest part )
    <receiver android:name=".BatteryCare_Broadcast"
            android:enabled="true"
            android:exported="true">
        <intent-filter>
            <action android:name=".BatteryCare_Broadcast"/>
        </intent-filter>
        </receiver>

        <receiver android:name=".DisChar_Broadcast"
            android:enabled="true"
            android:exported="true">
        <intent-filter>
            <action android:name=".DisChar_Broadcast"/>
        </intent-filter>
        </receiver>

*/



