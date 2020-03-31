package com.example.rosen.facdom;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.serviceOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(ScreenChangeService.class);
            }
        });

        findViewById(R.id.serviceOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(ScreenChangeService.class);
            }
        });
    }

    private void startService(Class service) {
        Intent backgroundService = new Intent(this, service);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(backgroundService);
        } else {
            startService(backgroundService);
        }
    }

    private void stopService(Class service) {
        Intent backgroundService = new Intent(MainActivity.this, service);
        stopService(backgroundService);
    }
}
