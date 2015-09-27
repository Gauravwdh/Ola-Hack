package com.travelgeeks.olahackathon;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.travelgeeks.olahackathon.notification.NotificationData;
import com.travelgeeks.olahackathon.notification.NotificationHandler;
import com.travelgeeks.olahackathon.utilities.Logger;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GcmIntentService extends IntentService {

    private static final String TAG = GcmIntentService.class.getName();

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        if (extras != null && !extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Logger.d(TAG, "Message type error notification received");
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                Logger.d(TAG, "Message type deleted notification received");
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Log.d(TAG, "GCM: " + extras);
                NotificationHandler.buildNotification(this, new NotificationData(extras.getString("id")));
            }
        }
    }
}
