package com.travelgeeks.olahackathon.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.travelgeeks.olahackathon.notification.NotificationData;
import com.travelgeeks.olahackathon.notification.NotificationHandler;
import com.travelgeeks.olahackathon.utilities.Logger;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class BookCabLaterBroadCardReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.ex(System.currentTimeMillis());
        NotificationHandler.buildNotification(context, new NotificationData(intent.getStringExtra(DelayNotificationReceiver.REQUEST_ID)));
    }
}
