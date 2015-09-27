package com.travelgeeks.olahackathon.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.travelgeeks.olahackathon.notification.NotificationHandler;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class DismissBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ApplicationPreference.getInstance().put(ApplicationPreference.LAST_EVENT_TIME, System.currentTimeMillis());
        NotificationHandler.cancelNotification(context);
    }
}
