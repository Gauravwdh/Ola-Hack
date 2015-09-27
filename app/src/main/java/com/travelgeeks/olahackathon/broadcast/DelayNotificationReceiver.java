package com.travelgeeks.olahackathon.broadcast;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.travelgeeks.olahackathon.notification.NotificationHandler;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.Logger;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class DelayNotificationReceiver extends BroadcastReceiver {

    private static final int REPEAT_TIME = 15 * 60 * 1000;
    private static final int MAX_REPEAT_COUNT = 3;

    public static final String REQUEST_ID = "request_id";

    @Override

    public void onReceive(Context context, Intent intent) {
        NotificationHandler.cancelNotification(context);

        String key = intent.getStringExtra(REQUEST_ID);
        int count = ApplicationPreference.getInstance().get(key, 0);
        if (count > MAX_REPEAT_COUNT) {
            return;
        }
        ApplicationPreference.getInstance().put(key, count + 1);
        Intent notificationIntent = new Intent(context, BookCabLaterBroadCardReceiver.class);
        notificationIntent.putExtra(REQUEST_ID, key);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Logger.ex(System.currentTimeMillis());
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + REPEAT_TIME, broadcast);
    }
}
