package com.thimanucreations.batterycure; /*

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.ALARM_SERVICE;


public class BatteryCare_Broadcast extends BroadcastReceiver {
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)



    @Override
    public void onReceive(Context context1, Intent intent) {


            Intent noti_fullcharged = new Intent(context1, Settings.class);
            noti_fullcharged.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent1 =
                    PendingIntent.getActivity(context1, 0, noti_fullcharged, PendingIntent.FLAG_UPDATE_CURRENT);

          /*  Intent fullScreenIntent = new Intent(context1, MainActivity.class);
            PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context1, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
          //  String CHANNEL_ID = "1234";
            Uri Batfullsound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context1.getPackageName() + "/" + R.raw.batteryfull);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel mChannel = new NotificationChannel("notifyBatteryFull",
                    "Battery_full_channel ",
                    NotificationManager.IMPORTANCE_DEFAULT);

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            NotificationChannel mChannel = new NotificationChannel("notifyBatteryCare",
                    context1.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            mChannel.setDescription("msg");
            //mChannel.enableLights(true);
            //mChannel.enableVibration(true);
            mChannel.setSound(sound, attributes); // This is IMPORTANT


            NotificationManager mNotificationManager = null;
            if (mNotificationManager != null)
                mNotificationManager.createNotificationChannel(mChannel);
            } else


//for pre-oreo mobiles
            {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setContentTitle(context.getString(R.string.app_name))    .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity_.class), 0))
                    .setContentText("temporary text")
                    .setAutoCancel(true)
                    .setSound(Uri.parse("android.resource://"
                            + context.getPackageName() + "/"
                            + R.raw.alert))
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_stat_notification);

            Notification notification = builder.build();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, notification);



            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context1, "notifyBatteryCare")
                            .setSmallIcon(R.drawable.ic_baseline_battery_full_24)
                          //.setWhen(System.currentTimeMillis())
                            .setContentTitle("BatteryCare Notification (Battery Full)")
                            .setContentText("Battery is Fully Charged. Please Disconnect the charger")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setDefaults(Notification.DEFAULT_LIGHTS)
                            //.setSound(Uri.parse("android.resource://" + context1 + "/" + R.raw.batteryfull));
                            .setContentIntent(pendingIntent1)
                         // .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context1.getPackageName() + "/" + R.raw.batteryfull))
                          //.setFullScreenIntent(fullScreenPendingIntent, true)
                          //.setSound(sound)
                            .setAutoCancel(true);


        NotificationManager notificationManager =
                (NotificationManager) context1.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel =
                    new NotificationChannel("2825", "BATTERYFULL_NOTIFI", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationChannel.setSound(Batfullsound, audioAttributes);
            builder.setChannelId("2825");

            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        assert notificationManager != null;
        notificationManager.notify((int) System.currentTimeMillis(),
                builder.build());



            //Notification notification = builder.build();
            //notification.sound = Uri.parse("android.resource://" + context1.getPackageName() + "/" + R.raw.batteryfull);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Batteryfull";
            String description = "Channel for Battery_full notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel =
                    new NotificationChannel("notifyBatteryFull", name, importance);
            channel.setDescription(description);

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            //channel.enableLights(true);
            //channel.enableVibration(true);
            channel.setSound(sound, attributes);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager mnotificationManager = getSystemService(NotificationManager.class);
            mnotificationManager.createNotificationChannel(channel);

        } *

            NotificationManagerCompat mnotificationManager =
                    NotificationManagerCompat.from(context1);
            mnotificationManager.notify(200, builder.build());



    }

    //private NotificationManager getSystemService(Class<NotificationManager> notificationManagerClass) {
        //return null;

}   */
