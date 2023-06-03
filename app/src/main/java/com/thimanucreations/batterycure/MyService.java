package com.thimanucreations.batterycure;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {

    private BroadcastReceiver batteryBroCastRece;
    int i;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        batteryBroCastRece = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context0, Intent intent0) {
                Integer level = intent0.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                Integer status = intent0.getIntExtra(BatteryManager.EXTRA_STATUS, 0);

                if (status == BatteryManager.BATTERY_STATUS_FULL) {

                            Intent notifBatfull = new Intent(context0, Settings.class);
                            notifBatfull.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent0 =
                                    PendingIntent.getActivity(context0, 0, notifBatfull, PendingIntent.FLAG_UPDATE_CURRENT);

                            Uri FullcharSound =
                                    Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context0.getPackageName() + "/" + R.raw.batteryfull);

                            NotificationCompat.Builder builder0 =
                                    new NotificationCompat.Builder(context0, "notifyBatteryCare")
                                            .setSmallIcon(R.drawable.ic_baseline_battery_full_24)
                                            .setContentTitle("🔋 ✅ BatteryCare Notification (Battery Full)")
                                            .setContentText("Your Battery is Fully Charged. Please Disconnect the charger \nTo stop the this tap on the notification ")
                                            .setStyle(new NotificationCompat.BigTextStyle()
                                                    .bigText("Your Battery is Fully Charged. Please Disconnect the charger \nTo stop the this tap on the notification "))
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                            .setContentIntent(pendingIntent0)
                                            .setAutoCancel(true);

                            NotificationManager NotificationManager0 =
                                    (NotificationManager) context0.getSystemService(Context.NOTIFICATION_SERVICE);

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                AudioAttributes audioAttributes0 = new AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                        .setUsage(AudioAttributes.USAGE_ALARM)
                                        .build();

                                int importance0 = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel notificationChannel0 =
                                        new NotificationChannel("2428", "FULL", importance0);
                                notificationChannel0.enableLights(true);
                                notificationChannel0.setLightColor(Color.RED);
                                notificationChannel0.enableVibration(true);
                                notificationChannel0.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                                notificationChannel0.setSound(FullcharSound, audioAttributes0);
                                builder0.setChannelId("2428");

                                assert NotificationManager0 != null;
                                NotificationManager0.createNotificationChannel(notificationChannel0);
                            }
                            assert NotificationManager0 != null;
                            NotificationManager0.notify((int) System.currentTimeMillis(),
                                    builder0.build());


                            NotificationManagerCompat notificationManager0 =
                                    NotificationManagerCompat.from(context0);
                            notificationManager0.notify(300, builder0.build());


                }

                if(status == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                    if((level <= 50) && (level > 48)) {


                            Intent DisCharg = new Intent(context0, Settings.class);
                            DisCharg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent0 =
                                    PendingIntent.getActivity(context0, 0, DisCharg, PendingIntent.FLAG_UPDATE_CURRENT);

                            Uri DisCharSound =
                                    Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context0.getPackageName() + "/" + R.raw.discharging);

                            NotificationCompat.Builder builderA =
                                    new NotificationCompat.Builder(context0, "notifyBatteryCare")
                                            .setSmallIcon(R.drawable.ic_baseline_battery_unknown_24)
                                            .setContentTitle("⏬ BatteryCare Notification")
                                            .setContentText("Your Battery is Discharging. Please Connect the charger \nTo stop the this tap on the notification ")
                                            .setStyle(new NotificationCompat.BigTextStyle()
                                                    .bigText("Your Battery is Discharging. Please Connect the charger \nTo stop the this tap on the notification "))
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                            .setContentIntent(pendingIntent0)
                                            .setAutoCancel(true);

                            NotificationManager NotificationManagerA =
                                    (NotificationManager) context0.getSystemService(Context.NOTIFICATION_SERVICE);

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                AudioAttributes audioAttributesA = new AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                        .setUsage(AudioAttributes.USAGE_ALARM)
                                        .build();

                                int importanceA = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel notificationChannelA =
                                        new NotificationChannel("2429", "DISCHARGE", importanceA);
                                notificationChannelA.enableLights(true);
                                notificationChannelA.setLightColor(Color.RED);
                                notificationChannelA.enableVibration(true);
                                notificationChannelA.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                                notificationChannelA.setSound(DisCharSound, audioAttributesA);
                                builderA.setChannelId("2429");

                                assert NotificationManagerA != null;
                                NotificationManagerA.createNotificationChannel(notificationChannelA);
                            }
                            assert NotificationManagerA != null;
                            NotificationManagerA.notify((int) System.currentTimeMillis(),
                                    builderA.build());


                            NotificationManagerCompat notificationManagerA =
                                    NotificationManagerCompat.from(context0);
                            notificationManagerA.notify(400, builderA.build());

                    }
                }

                if(status == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                    if((level <= 30) && (level>28)) {


                            Intent LowCharg = new Intent(context0, Settings.class);
                            LowCharg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent0 =
                                    PendingIntent.getActivity(context0, 0, LowCharg, PendingIntent.FLAG_UPDATE_CURRENT);

                            Uri LowCharSound =
                                    Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context0.getPackageName() + "/" + R.raw.battery_low);

                            NotificationCompat.Builder builderB =
                                    new NotificationCompat.Builder(context0, "notifyBatteryCare")
                                            .setSmallIcon(R.drawable.ic_baseline_battery_unknown_24)
                                            .setContentTitle("⚠ BatteryCare Notification")
                                            .setContentText("Your Battery level is low. Please connect the charger \nTo stop the this tap on the notification ")
                                            .setStyle(new NotificationCompat.BigTextStyle()
                                                    .bigText("Your Battery level is low. Please connect the charger \nTo stop the this tap on the notification "))
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                            .setContentIntent(pendingIntent0)
                                            .setAutoCancel(true);

                            NotificationManager NotificationManagerB =
                                    (NotificationManager) context0.getSystemService(Context.NOTIFICATION_SERVICE);

                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                AudioAttributes audioAttributesB = new AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                        .setUsage(AudioAttributes.USAGE_ALARM)
                                        .build();

                                int importanceB = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel notificationChannelB =
                                        new NotificationChannel("2430", "LOW", importanceB);
                                notificationChannelB.enableLights(true);
                                notificationChannelB.setLightColor(Color.RED);
                                notificationChannelB.enableVibration(true);
                                notificationChannelB.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                                notificationChannelB.setSound(LowCharSound, audioAttributesB);
                                builderB.setChannelId("2430");

                                assert NotificationManagerB != null;
                                NotificationManagerB.createNotificationChannel(notificationChannelB);
                            }
                            assert NotificationManagerB != null;
                            NotificationManagerB.notify((int) System.currentTimeMillis(),
                                    builderB.build());


                            NotificationManagerCompat notificationManagerB =
                                    NotificationManagerCompat.from(context0);
                            notificationManagerB.notify(500, builderB.build());

                    }
                }

                if(status  == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                        if((level<=20) && (level>18)) {


                                Intent CriticalCharg = new Intent(context0, Settings.class);
                                CriticalCharg.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent pendingIntent0 =
                                        PendingIntent.getActivity(context0, 0, CriticalCharg, PendingIntent.FLAG_UPDATE_CURRENT);

                                Uri CriCharSound =
                                        Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context0.getPackageName() + "/" + R.raw.critical_battery);

                                NotificationCompat.Builder builderC =
                                        new NotificationCompat.Builder(context0, "notifyBatteryCare")
                                                .setSmallIcon(R.drawable.ic_baseline_battery_alert_24)
                                                .setContentTitle("❗❗❗ BatteryCare Notification")
                                                .setContentText("Your Battery is in Critical status. Please connect the Charger immediately \nTo stop the this tap on the notification ")
                                                .setStyle(new NotificationCompat.BigTextStyle()
                                                        .bigText("Your Battery is in Critical status. Please connect the Charger immediately \nTo stop the this tap on the notification "))
                                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                                .setContentIntent(pendingIntent0)
                                                .setAutoCancel(true);

                                NotificationManager NotificationManagerC =
                                        (NotificationManager) context0.getSystemService(Context.NOTIFICATION_SERVICE);

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    AudioAttributes audioAttributesC = new AudioAttributes.Builder()
                                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                            .setUsage(AudioAttributes.USAGE_ALARM)
                                            .build();

                                    int importanceC = NotificationManager.IMPORTANCE_HIGH;
                                    NotificationChannel notificationChannelC =
                                            new NotificationChannel("2431", "CRITICAL", importanceC);
                                    notificationChannelC.enableLights(true);
                                    notificationChannelC.setLightColor(Color.RED);
                                    notificationChannelC.enableVibration(true);
                                    notificationChannelC.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                                    notificationChannelC.setSound(CriCharSound, audioAttributesC);
                                    builderC.setChannelId("2431");

                                    assert NotificationManagerC != null;
                                    NotificationManagerC.createNotificationChannel(notificationChannelC);
                                }
                                assert NotificationManagerC != null;
                                NotificationManagerC.notify((int) System.currentTimeMillis(),
                                        builderC.build());


                                NotificationManagerCompat notificationManagerC =
                                        NotificationManagerCompat.from(context0);
                                notificationManagerC.notify(600, builderC.build());

                        }
                }
            }
        };

        registerReceiver(batteryBroCastRece, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
