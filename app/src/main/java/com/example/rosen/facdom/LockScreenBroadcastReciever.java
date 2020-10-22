package com.example.rosen.facdom;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class LockScreenBroadcastReciever extends BroadcastReceiver {
    private Notification notification;

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_SCREEN_ON:
                if (notification != null) {
                    NotificatonGenerator.showNotification(notification, context);
                }
                break;
            case Intent.ACTION_SCREEN_OFF:
                factGenerator.getRandomFact((fact, title) -> {
                    if (fact != null) {
                        notification = NotificatonGenerator.generateNotification(title, fact.getContent(), getPendingIntentFromFact(fact, context), context);
                    }
                });
                break;
        }
    }

    public PendingIntent getPendingIntentFromFact(fact Fact, Context context) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(Fact.getSrc()));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return PendingIntent.getActivity(context, 0, i, 0);
    }
}
