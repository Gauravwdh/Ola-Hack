package com.travelgeeks.olahackathon.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.travelgeeks.olahackathon.service.LocationService;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class RestartBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent service = PendingIntent.getService(context, 0, new Intent(context, LocationService.class), PendingIntent
                .FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1 * 60 * 1000, service);
    }
}
