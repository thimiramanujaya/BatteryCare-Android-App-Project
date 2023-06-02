package com.thimanucreations.batterycure;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.thimanucreations.batterycure.Settings.capacityInt;
import static com.thimanucreations.batterycure.Settings.capacityIntIndex;
import static com.thimanucreations.batterycure.Settings.energyInt;

public class MainActivity extends Activity {

    TextView curr_lvl, indi, status, work, additional, health, healthIndex, temp, tempIndex, volt, voltIndex, method, methodIndex, txt1, txt2, txt3, heathLevel, timeBattery, timeBatteryDisplay;
    Button button;
    ImageButton setting, healthLevelQue;
    ProgressBar progressBar0;
    ImageView tenI,twentyI,thirtyI,fourtyI,fiftyI,sixtyI,seventyI,eightyI,ninetyI,hundredI;
    VideoView charg_anim, discharg_anim, bat_bg;
    IntentFilter intentfilter;
    View div;
    int deviceStatus;
    String currentBatteryStatus="Battery Info";
    int batteryLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  createNotificationChannel();


        button = (Button) findViewById(R.id.button);
        setting = (ImageButton) findViewById(R.id.imageButton);
        healthLevelQue = (ImageButton) findViewById(R.id.healthQue);
        curr_lvl = (TextView) findViewById(R.id.textView6);
        indi = (TextView) findViewById(R.id.level_index);
        tenI = (ImageView) findViewById(R.id.ten);
        twentyI = (ImageView) findViewById(R.id.twenty);
        thirtyI = (ImageView) findViewById(R.id.thirty);
        fourtyI = (ImageView) findViewById(R.id.fourty);
        fiftyI = (ImageView) findViewById(R.id.fifty);
        sixtyI = (ImageView) findViewById(R.id.sixty);
        seventyI = (ImageView) findViewById(R.id.seventy);
        eightyI = (ImageView) findViewById(R.id.eighty);
        ninetyI = (ImageView) findViewById(R.id.ninety);
        hundredI = (ImageView) findViewById(R.id.hundred);
        status = (TextView) findViewById(R.id.textView7);
        work = (TextView) findViewById(R.id.textView9);
        txt1 = (TextView) findViewById(R.id.textView4);
        txt2 = (TextView) findViewById(R.id.textView5);
        txt3 = (TextView) findViewById(R.id.textView8);
        additional = (TextView) findViewById(R.id.textView11);
        div = (View) findViewById(R.id.divider2);
        health =(TextView) findViewById(R.id.textView12);
        healthIndex =(TextView) findViewById(R.id.textView13);
        temp =(TextView) findViewById(R.id.textView14);
        tempIndex =(TextView) findViewById(R.id.textView15);
        volt =(TextView) findViewById(R.id.textView16);
        voltIndex =(TextView) findViewById(R.id.textView17);
        method =(TextView) findViewById(R.id.textView18);
        methodIndex =(TextView) findViewById(R.id.textView19);
        heathLevel =(TextView) findViewById(R.id.health);
        timeBattery =(TextView) findViewById(R.id.timeCharge);
        timeBatteryDisplay =(TextView) findViewById(R.id.timeChargeDisplay);
        progressBar0 = (ProgressBar) findViewById(R.id.progressBar);
        charg_anim = (VideoView) findViewById(R.id.videoView);
        String anim = "android.resource://" + getPackageName() + "/" + R.raw.charging_anim;
        Uri uri = Uri.parse(anim);
        charg_anim.setVideoURI(uri);

        discharg_anim = (VideoView) findViewById(R.id.videoView2);
        String anim2 = "android.resource://" + getPackageName() + "/" + R.raw.discharging_anim;
        Uri uri2 = Uri.parse(anim2);
        discharg_anim.setVideoURI(uri2);

        bat_bg = (VideoView) findViewById(R.id.videoView3);
        String anim3 = "android.resource://" + getPackageName() + "/" + R.raw.battery_bg;
        Uri uri3 = Uri.parse(anim3);
        bat_bg.setVideoURI(uri3);

        bat_bg.start();
        bat_bg.resume();

        heathLevel.setText("Battery Health :- Unknown");

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Settings.class);
                startActivity(i);
            }
        });

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


        MainActivity.this.registerReceiver(broadcastreceiver, intentfilter);


      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setVisibility(View.VISIBLE);
                txt2.setVisibility(View.VISIBLE);
                txt3.setVisibility(View.VISIBLE);
                curr_lvl.setVisibility(View.VISIBLE);
                status.setVisibility(View.VISIBLE);
                work.setVisibility(View.VISIBLE);
                work.setFocusable(true);
                work.setFocusableInTouchMode(true);
                work.requestFocus();

                Intent intent = new Intent(MainActivity.this,BatteryCare_Broadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            long pre_time = System.currentTimeMillis();
            long fiveSecondsInMillis = 1000*5;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    pre_time + fiveSecondsInMillis,
                    pendingIntent);

            }
        });  */


    }


        private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryLevel = (int) (((float) level / (float) scale) * 100.0f);
                int healthstatus = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
                float temperature = (float) (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1)/10);
                float voltage = (float) (intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1)*0.001);
                int Chargingmethod = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);

    // 1.) Battery Health calculation by Capacity.   (changing continuously)
              long maxCapacity = getBatteryCapacity();

                long decreseCapacity = (long)((float)capacityInt- (float)maxCapacity);
                long healthPercentage = (long) (((float) maxCapacity * 100.0f) / (float) capacityInt);



    // 2.) Battery Health calculation by Energy and Capacity   (not precise.also changing)
           /*   long remainCapacity = getBatteryCapacity();

                long maxCapacity = (long) ((float)remainCapacity*100.0f)/batteryLevel;

                long decreseCapacity = (long)((float)energyInt-(float) maxCapacity);
                long healthPercentage = (long)(((float) maxCapacity *100.0f)/(float)energyInt);  */


    // 3.) Battery Health calculation by Energy.                // app is going to crash. remainEnergy is wrong value. #REVIEW
          /*      float remainEnergy = getBatteryCapacity();

                int fullEnergy = (int)((int)remainEnergy*100)/batteryLevel;

                int decreseCapacity = (int)((int)(energyInt + fullEnergy)/10000);      // fullEnergy is (-) value
                int healthPercentage =(int)((int) fullEnergy*(-100))/((int)energyInt*10);  */


    // 4.) using Java's Reflection APIs

              /*double fullremainCapacity = getBatteryCapacity(context);

                long decreseCapacity = (long)((float)energyInt-(float)fullremainCapacity);
                long healthPercentage = (long)(((float)fullremainCapacity*100.0f)/(float)energyInt); */


                float timeforFullCharge = computeChargeTimeRemaining();

                int miliSecC = (int)timeforFullCharge%1000;
                int secC = (int)(timeforFullCharge/1000)%60;
                int minC = (int) (((int)(timeforFullCharge/1000)/60)%60);
                int hoursC = (int)((((int)(timeforFullCharge/1000)/60)/60)%24);
                int daysC = (int)((((int)(timeforFullCharge/1000)/60)/60)/24);

                float timeforFullDisCharge = computeDisChargeTimeRemaining();

                int miliSecDC = (int)timeforFullDisCharge%1000;
                int secDC = (int)(timeforFullDisCharge/1000)%60;
                int minDC = (int) (((int)(timeforFullDisCharge/1000)/60)%60);
                int hoursDC = (int)((((int)(timeforFullDisCharge/1000)/60)/60)%24);
                int daysDC = (int)((((int)(timeforFullDisCharge/1000)/60)/60)/24);

                progressBar0.setProgress(batteryLevel);

                if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING) {

                    curr_lvl.setText(currentBatteryStatus + " : Charging at " + batteryLevel + " %");
                    indi.setText(+batteryLevel + " %");
                    charg_anim.start();
                    charg_anim.setVisibility(View.VISIBLE);
                    charg_anim.resume();
                    discharg_anim.setVisibility(View.INVISIBLE);
                    timeBattery.setText("Time left until fully Charged");
                    timeBatteryDisplay.setText(daysC +" d  :  "+hoursC +" hr  :  "+minC+" min");  //  : "+secC+"sec : "+miliSecC+"ms"

                }

                if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                    curr_lvl.setText(currentBatteryStatus + " : Discharging at " + batteryLevel + " %");
                    indi.setText(+batteryLevel + " %");
                    discharg_anim.start();
                    discharg_anim.setVisibility(View.VISIBLE);
                    discharg_anim.resume();
                    charg_anim.setVisibility(View.INVISIBLE);
                    timeBattery.setText("Time remaining until fully Discharged");
                    timeBatteryDisplay.setText(daysDC +" d  :  "+hoursDC +" hr  :  "+minDC+" min");  // : "+secDC+"sec : "+miliSecDC+"ms"
                }


                if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL) {

                    curr_lvl.setText(currentBatteryStatus + ": Battery Full at " + batteryLevel + " %");
                    indi.setText(+batteryLevel + " %");
                    charg_anim.start();
                    charg_anim.setVisibility(View.VISIBLE);
                    charg_anim.pause();
                    discharg_anim.setVisibility(View.INVISIBLE);
                    bat_bg.setVisibility(View.INVISIBLE);
                    timeBattery.setText("Phone Battery is fully Charged");
                    timeBatteryDisplay.setText("Not Available");

                /*    Intent intent1 = new Intent(MainActivity.this,BatteryCare_Broadcast.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent1,0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long pre_time = System.currentTimeMillis();
                    long fiveSecondsInMillis = 1000*5;

                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            pre_time + fiveSecondsInMillis,
                            pendingIntent);


                    Intent intent1 = new Intent(MainActivity.this,BatteryCare_Broadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent1,0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                long pre_time = S ystem.currentTimeMillis();
                long twoSecondsInMillis = 1000*2;

                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        pre_time + twoSecondsInMillis,
                        pendingIntent);

                Intent noti_fullcharged = new Intent(context,Settings.class);
                noti_fullcharged.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,0, noti_fullcharged, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyBatteryCare")
                        .setSmallIcon(R.drawable.ic_baseline_battery_alert_24)
                        .setContentTitle("BatteryCare Notification (Battery Full)")
                        .setContentText("Your Battery is Fully Charged. Please Unplug the charger")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(100, builder.build()); */


                }


                if (deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN) {

                    curr_lvl.setText(currentBatteryStatus + " : Unknown at " + batteryLevel + " %");
                    indi.setText(+batteryLevel + " %");
                    timeBattery.setText("Battery is status is unknown");
                    timeBatteryDisplay.setText("Not Available");
                }


                if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {

                    curr_lvl.setText(currentBatteryStatus + " : Not Charging at " + batteryLevel + " %");
                    indi.setText(+batteryLevel + " %");
                    timeBattery.setText("Phone Battery is not Charging");
                    timeBatteryDisplay.setText("Not Available");
                }


                if (batteryLevel >= 0) {
                    tenI.setVisibility(View.VISIBLE);
                    status.setText(" Critical ");
                    status.setTextColor(Color.parseColor("#FFFFFF"));
                    status.setBackgroundResource(R.drawable.red);
                    work.setText(" Plug the charger immediately ");
                    work.setTextColor(Color.parseColor("#FFFFFF"));
                    work.setBackgroundResource(R.drawable.red);
                    curr_lvl.setTextColor(Color.parseColor("#FFFFFF"));
                    curr_lvl.setBackgroundResource(R.drawable.red);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#FFFFFF"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.red);

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING)
                        work.setText(" Well, keep charging until full");


                   /* if (batteryLevel == 10) {

                        if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                            Intent noti_fullcharged = new Intent(context, Settings.class);
                            noti_fullcharged.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent =
                                    PendingIntent.getActivity(context, 0, noti_fullcharged, PendingIntent.FLAG_UPDATE_CURRENT);

                            NotificationCompat.Builder builder =
                                    new NotificationCompat.Builder(context, "notifyBatteryCare")
                                            .setSmallIcon(R.drawable.ic_baseline_battery_alert_24)
                                            .setContentTitle("BatteryCare Notification (Battery in Critical)")
                                            .setContentText("Your Battery is in Critical State. Please Plug the charger immediately")
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true);

                            NotificationManagerCompat notificationManager =
                                    NotificationManagerCompat.from(context);
                            notificationManager.notify(100, builder.build());


                        }
                    } */


                }
                if (batteryLevel >= 10) {
                    twentyI.setVisibility(View.VISIBLE);
                    status.setText(" Danger ");
                    status.setBackgroundResource(R.drawable.red);
                    status.setTextColor(Color.parseColor("#FFFFFF"));
                    work.setText(" Battery charging is highly required ");
                    work.setBackgroundResource(R.drawable.red);
                    work.setTextColor(Color.parseColor("#FFFFFF"));
                    curr_lvl.setTextColor(Color.parseColor("#FFFFFF"));
                    curr_lvl.setBackgroundResource(R.drawable.red);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#FFFFFF"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.red);

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING)
                        work.setText("Well, keep charging until full");

                }
                if (batteryLevel >= 20) {
                    thirtyI.setVisibility(View.VISIBLE);
                    status.setText(" Worse ");
                    status.setBackgroundResource(R.drawable.orange);
                    status.setTextColor(Color.parseColor("#FFFFFF"));
                    work.setText(" Terminate your all works ");
                    work.setBackgroundResource(R.drawable.orange);
                    work.setTextColor(Color.parseColor("#FFFFFF"));
                    curr_lvl.setBackgroundResource(R.drawable.orange);
                    curr_lvl.setTextColor(Color.parseColor("#FFFFFF"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.orange);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#FFFFFF"));

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING)
                        work.setText("Good, keep charging until full");
                }
                if (batteryLevel >= 30) {
                    fourtyI.setVisibility(View.VISIBLE);
                    status.setText(" Bad ");
                    status.setBackgroundResource(R.drawable.light_orange);
                    status.setTextColor(Color.parseColor("#000000"));
                    work.setText(" Eliminate your works ");
                    work.setBackgroundResource(R.drawable.light_orange);
                    work.setTextColor(Color.parseColor("#000000"));
                    curr_lvl.setBackgroundResource(R.drawable.light_orange);
                    curr_lvl.setTextColor(Color.parseColor("#000000"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.light_orange);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#000000"));

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING)
                        work.setText("Good, keep charging until full");
                    /*if (batteryLevel == 30) {

                        if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                            Intent noti_fullcharged = new Intent(context, Settings.class);
                            noti_fullcharged.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent =
                                    PendingIntent.getActivity(context, 0, noti_fullcharged, PendingIntent.FLAG_UPDATE_CURRENT);

                            NotificationCompat.Builder builder =
                                    new NotificationCompat.Builder(context, "notifyBatteryCare")
                                            .setSmallIcon(R.drawable.ic_baseline_battery_unknown_24)
                                            .setContentTitle("BatteryCare Notification")
                                            .setContentText("Your Battery level is low. Please plug the charger ")
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true);

                            NotificationManagerCompat notificationManager =
                                    NotificationManagerCompat.from(context);
                            notificationManager.notify(100, builder.build());
                        }

                    } */

                }
                if (batteryLevel >= 45) {
                    fiftyI.setVisibility(View.VISIBLE);
                    status.setText(" Warning ");
                    status.setBackgroundResource(R.drawable.yellow);
                    status.setTextColor(Color.parseColor("#000000"));
                    work.setText(" Battery Charging is required ");
                    work.setBackgroundResource(R.drawable.yellow);
                    work.setTextColor(Color.parseColor("#000000"));
                    curr_lvl.setBackgroundResource(R.drawable.yellow);
                    curr_lvl.setTextColor(Color.parseColor("#000000"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.yellow);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#000000"));

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING)
                        work.setText("Good, keep charging until full");

                  /*  if (batteryLevel == 45) {
                        if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {

                            Intent noti_fullcharged = new Intent(context, Settings.class);
                            noti_fullcharged.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent =
                                    PendingIntent.getActivity(context, 0, noti_fullcharged, PendingIntent.FLAG_UPDATE_CURRENT);

                            NotificationCompat.Builder builder =
                                    new NotificationCompat.Builder(context, "notifyBatteryCare")
                                            .setSmallIcon(R.drawable.ic_baseline_battery_unknown_24)
                                            .setContentTitle("BatteryCare Notification")
                                            .setContentText("Your Battery  is discharging. Please plug the charger ")
                                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                            .setContentIntent(pendingIntent)
                                            .setAutoCancel(true);

                            NotificationManagerCompat notificationManager =
                                    NotificationManagerCompat.from(context);
                            notificationManager.notify(100, builder.build());
                        }
                    } */


                }

                if (batteryLevel >= 58) {
                    sixtyI.setVisibility(View.VISIBLE);
                    status.setText(" Normal ");
                    status.setTextColor(Color.parseColor("#000000"));
                    status.setBackgroundResource(R.drawable.txtview_bg);
                    work.setText(" Work on, Better to charging  ");
                    work.setTextColor(Color.parseColor("#000000"));
                    work.setBackgroundResource(R.drawable.txtview_bg);
                    curr_lvl.setBackgroundResource(R.drawable.txtview_bg);
                    curr_lvl.setTextColor(Color.parseColor("#000000"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.txtview_bg);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#000000"));

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING)
                        work.setText("Good, keep charging until full");


                }

                if (batteryLevel >= 70) {
                    seventyI.setVisibility(View.VISIBLE);
                    status.setText(" Good ");
                    work.setText(" Work on ");
                    status.setTextColor(Color.parseColor("#000000"));
                    status.setBackgroundResource(R.drawable.txtview_bg);
                    work.setTextColor(Color.parseColor("#000000"));
                    work.setBackgroundResource(R.drawable.txtview_bg);
                    curr_lvl.setBackgroundResource(R.drawable.txtview_bg);
                    curr_lvl.setTextColor(Color.parseColor("#000000"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.txtview_bg);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#000000"));
                }

                if (batteryLevel >= 80) {
                    eightyI.setVisibility(View.VISIBLE);
                    status.setText(" Better Charge ");
                    status.setBackgroundResource(R.drawable.light_greeen);
                    status.setTextColor(Color.parseColor("#000000"));
                    work.setText(" Perform your works ");
                    work.setBackgroundResource(R.drawable.light_greeen);
                    work.setTextColor(Color.parseColor("#000000"));
                    curr_lvl.setBackgroundResource(R.drawable.light_greeen);
                    curr_lvl.setTextColor(Color.parseColor("#000000"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.light_greeen);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#000000"));



                      /*      Intent intent3 = new Intent(MainActivity.this,DisChar_Broadcast.class);
                            PendingIntent pendingIntent3 = PendingIntent.getBroadcast(MainActivity.this,0,intent3,0);

                            AlarmManager alarmManager3 = (AlarmManager) getSystemService(ALARM_SERVICE);
                            long pre_time3 = System.currentTimeMillis();
                            long fiveSecondsInMillis3 = 1000*5;

                            alarmManager3.set(AlarmManager.RTC_WAKEUP,
                                    pre_time3 + fiveSecondsInMillis3,
                                    pendingIntent3);  */


                }

                if (batteryLevel >= 90) {
                    ninetyI.setVisibility(View.VISIBLE);
                    status.setText(" Best Performance ");
                    status.setBackgroundResource(R.drawable.light_greeen);
                    status.setTextColor(Color.parseColor("#000000"));
                    work.setText(" Perform any work in high efficient ");
                    work.setBackgroundResource(R.drawable.light_greeen);
                    work.setTextColor(Color.parseColor("#000000"));
                    curr_lvl.setBackgroundResource(R.drawable.light_greeen);
                    curr_lvl.setTextColor(Color.parseColor("#000000"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.light_greeen);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#000000"));

                 /*   Intent intent3 = new Intent(MainActivity.this,DisChar_Broadcast.class);
                    PendingIntent pendingIntent3 = PendingIntent.getBroadcast(MainActivity.this,0,intent3,0);

                    AlarmManager alarmManager3 = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long pre_time3 = System.currentTimeMillis();
                    long fiveSecondsInMillis3 = 1000*5;

                    alarmManager3.set(AlarmManager.RTC_WAKEUP,
                            pre_time3 + fiveSecondsInMillis3,
                            pendingIntent3); */

                }

                if (batteryLevel == 100) {
                    hundredI.setVisibility(View.VISIBLE);
                    status.setTextColor(Color.parseColor("#FFFFFF"));
                    status.setText(" Fully Charged ");
                    status.setBackgroundResource(R.drawable.greeen);
                    work.setTextColor(Color.parseColor("#FFFFFF"));
                    work.setText(" Unplug your charger ");
                    work.setBackgroundResource(R.drawable.greeen);
                    curr_lvl.setTextColor(Color.parseColor("#FFFFFF"));
                    curr_lvl.setBackgroundResource(R.drawable.greeen);
                    timeBatteryDisplay.setTextColor(Color.parseColor("#FFFFFF"));
                    timeBatteryDisplay.setBackgroundResource(R.drawable.greeen);

                    if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING)
                        work.setText(" Perform any work in high efficient ");


                }

                switch (Chargingmethod) {

                    case BatteryManager.BATTERY_PLUGGED_AC:
                        methodIndex.setText("AC Charging");
                        methodIndex.setTextColor(Color.parseColor("#FFD600"));
                        break;

                    case BatteryManager.BATTERY_PLUGGED_USB:
                        methodIndex.setText("USB Charging");
                        methodIndex.setTextColor(Color.parseColor("#64DD17"));
                        break;

                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        methodIndex.setText("Wireless Charging");
                        break;

                    default:
                        methodIndex.setText("Not connect to charger");
                        methodIndex.setTextColor(Color.parseColor("#FFAB00"));
                        break;
                }

                if((temperature>15.0) && (temperature<25.0)) {
                    tempIndex.setText(+temperature + " C°");
                    tempIndex.setTextColor(Color.parseColor("#D50000"));
                }

                if((temperature>=25.0) && (temperature<30.0)) {
                    tempIndex.setText(+temperature + " C°");
                    tempIndex.setTextColor(Color.parseColor("#84FFFF"));
                }

                if((temperature>=30.0) && (temperature<37.0)) {
                    tempIndex.setText(+temperature + " C°");
                    tempIndex.setTextColor(Color.parseColor("#AEEA00"));
                }
                if((temperature>=37.0) && (temperature<45.0)) {
                    tempIndex.setText(+temperature + " C°");
                    tempIndex.setTextColor(Color.parseColor("#D50000"));
                }
                voltIndex.setText(+voltage+ " V");

                switch (healthstatus) {

                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        healthIndex.setText("Unknown❓");
                        healthIndex.setTextColor(Color.parseColor("#00BFA5"));
                        break;

                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        healthIndex.setText("Good ✔");
                        healthIndex.setTextColor(Color.parseColor("#64DD17"));
                        break;

                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        healthIndex.setText("OverHeat 💥");
                        healthIndex.setTextColor(Color.parseColor("#D50000"));
                        break;

                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        healthIndex.setText("Dead");
                        healthIndex.setTextColor(Color.parseColor("#D50000"));
                        break;

                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        healthIndex.setText("Over Voltage ⚠");
                        healthIndex.setTextColor(Color.parseColor("#FF6D00"));
                        break;

                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        healthIndex.setText("Unspecified Failure");
                        healthIndex.setTextColor(Color.parseColor("#FFD600"));
                        break;

                    case BatteryManager.BATTERY_HEALTH_COLD:
                        healthIndex.setText("Cold ✳");
                        healthIndex.setTextColor(Color.parseColor("#84FFFF"));
                        break;

                }

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt1.setVisibility(View.VISIBLE);
                        txt2.setVisibility(View.VISIBLE);
                        txt3.setVisibility(View.VISIBLE);
                        curr_lvl.setVisibility(View.VISIBLE);
                        status.setVisibility(View.VISIBLE);
                        work.setVisibility(View.VISIBLE);
                        additional.setVisibility(View.VISIBLE);
                        div.setVisibility(View.VISIBLE);
                        health.setVisibility(View.VISIBLE);
                        healthIndex.setVisibility(View.VISIBLE);
                        temp.setVisibility(View.VISIBLE);
                        tempIndex.setVisibility(View.VISIBLE);
                        volt.setVisibility(View.VISIBLE);
                        voltIndex.setVisibility(View.VISIBLE);
                        methodIndex.setVisibility(View.VISIBLE);
                        heathLevel.setVisibility(View.VISIBLE);
                        healthLevelQue.setVisibility(View.VISIBLE);
                        timeBattery.setVisibility(View.VISIBLE);
                        timeBatteryDisplay.setVisibility(View.VISIBLE);

                        method.setVisibility(View.VISIBLE);
                        method.setFocusable(true);
                        method.setFocusableInTouchMode(true);
                        method.requestFocus();

                        heathLevel.setText("Health: Click on❔to configure");

                        if(capacityIntIndex>0)
                        heathLevel.setText("Battery Health :  " +  healthPercentage + " %  " + "( -" + decreseCapacity + "↓)" );

                    healthLevelQue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getApplicationContext(),Settings.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Note: Input Battery capacity of your Phone",Toast.LENGTH_LONG).show();
                        }
                    });

                    }
                });

            }

        };


      private long getBatteryCapacity() {                // Return data type determine in here...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManagerC =
                    (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        //  long energy = batteryManagerC.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
            long ChargerCounter = batteryManagerC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
            long Capacity = batteryManagerC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);


          if (ChargerCounter == Long.MIN_VALUE || Capacity == Long.MIN_VALUE)
              return 0;

            // 1.) Method
              //   return (long) ((((float) ChargerCounter / (float) Capacity) * 100.0f) / 1000.0f);   // ChargeCounter = current variable capacity in uAh (This Value is Changing continuously)
                   return (long) ((float)ChargerCounter/1000.0f);

            // 2.) Method
            //  long value =  ChargerCounter/1000;
            //  return value;

            // 3.) Method
            //   float value = (float)energy/1000000000000.0f;    // ( The reason for fetching wrong Energy value can be happen with using of float ) (#solved.) Try using double(false)  (because energy unit : nWh 10e-9Wh)
            //   return value;
            }
            return 0;

    }

 /*  4.) Method      ( using Java's Reflection APIs )

    public double getBatteryCapacity(Context context) {
        Object mPowerProfile;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(context);

            batteryCapacity = (double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(mPowerProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return batteryCapacity;

    }  */

    private float computeChargeTimeRemaining () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManagerTFC =
                    (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
            float currentNowC =
                    batteryManagerTFC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
            long ChargerCounterTFC =
                    batteryManagerTFC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
            long CapacityTFC =
                    batteryManagerTFC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            long valueTFC = (long) (((float) ChargerCounterTFC / (float) CapacityTFC) * 100f);

            float timeFullCharge =
                    (float) (((float) valueTFC - (float) ChargerCounterTFC) / currentNowC);

            float timeINmsC = timeFullCharge * 3600.0f;

            return timeINmsC;

        }
        return 0;
    }

    private float computeDisChargeTimeRemaining () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManagerTFDC =
                    (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
            float currentNowDC =
                    batteryManagerTFDC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
            long ChargerCounterTFDC =
                    batteryManagerTFDC.getLongProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);

            float timeFullDisCharge =
                    (float) (((float) ChargerCounterTFDC) / currentNowDC);

            float timeINmsDC = timeFullDisCharge * (-3600.0f);

            return timeINmsDC;

        }
        return 0;
    }







  /*  private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "StudentChannel";
            String description = "Channel for Student notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyBatteryCare", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager =getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }  */

    /*@Override
    protected void onStop () {
        super.onStop();
        //if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL) {
        startService(new Intent(MainActivity.this, BatteryFull_Service.class));
        finish();
    }
     @Override
     protected void onDestroy () {
         startService(new Intent(MainActivity.this, BatteryFull_Service.class));
         super.onDestroy();
         finish();

        //}
        }*/


}