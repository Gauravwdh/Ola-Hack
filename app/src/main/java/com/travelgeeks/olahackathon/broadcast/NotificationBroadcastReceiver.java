package com.travelgeeks.olahackathon.broadcast;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.travelgeeks.olahackathon.notification.NotificationData;
import com.travelgeeks.olahackathon.notification.NotificationHandler;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationData notificationData = new NotificationData("" + System.currentTimeMillis());
        notificationData.title = "Event Upcoming";
        notificationData.content = intent.getStringExtra("data");
        NotificationHandler.buildNotification(context, notificationData);
    }
}
