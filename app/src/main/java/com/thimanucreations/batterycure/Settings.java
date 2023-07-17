package com.thimanucreations.batterycure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Button turnON, turnOFF;
    ImageButton rightCapacity;
    EditText phoneCapacity;

    public static long capacityInt, capacityIntIndex, energyInt ;
//  public static int capacityInt, capacityIntIndex, energyInt ;
//  public static double energyInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createNotificationChannel();

        turnOFF = findViewById(R.id.button3);
        turnON = findViewById(R.id.button2);
        rightCapacity = findViewById(R.id.imageButton2);
        phoneCapacity = findViewById(R.id.editTextNumber);


      /*  Button btnstop = findViewById(R.id.button2);
        MediaPlayer player = MediaPlayer.create(this,R.raw.batteryfull);

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                player.release();

            }
        }); */

        turnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Audio Notifications are turned ON",Toast.LENGTH_LONG).show();
                startService(new Intent(Settings.this, MyService.class));

            }
        });

        turnOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Audio Notifications are turned OFF",Toast.LENGTH_LONG).show();
                stopService(new Intent(Settings.this, MyService.class));

            }
        });



        rightCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //  1.) Battery Health Method
                capacityIntIndex = Long.parseLong(phoneCapacity.getText().toString());
                capacityInt = (long) ((float)capacityIntIndex + ((float)capacityIntIndex/50.0f));
                Toast.makeText(getApplicationContext(), "Battery capacity is acquired. Now check Battery Health",Toast.LENGTH_LONG).show();

            /*  2.) Battery Health Method
                capacityIntIndex = Long.parseLong(phoneCapacity.getText().toString());
                energyInt = (long) ((float)capacityIntIndex + ((float)capacityIntIndex/100.0f));  //3.80f);        // 3.80 is the Voltage (to convert energy)
                Toast.makeText(getApplicationContext(), "Battery capacity is acquired. Now check Battery Health",Toast.LENGTH_LONG).show(); */

            //  3.) Battery Health Method
            /*  capacityIntIndex = Integer.parseInt(phoneCapacity.getText().toString());
                energyInt = (int) ((int)(capacityIntIndex + (int)(capacityIntIndex/100))*1000*4);        // (3.80) or 4 is the Voltage (to convert energy)  *1000 is to convert (micro)uWh
                Toast.makeText(getApplicationContext(), "Battery capacity is acquired. Now check Battery Health",Toast.LENGTH_LONG).show();  */
            }
        });
    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "StudentChannel";
            String description = "Channel for Student notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyBatteryCare", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager =getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}