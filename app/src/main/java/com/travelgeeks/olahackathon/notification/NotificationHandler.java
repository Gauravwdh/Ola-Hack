package com.travelgeeks.olahackathon.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.travelgeeks.olahackathon.MainActivity;
import com.travelgeeks.olahackathon.R;
import com.travelgeeks.olahackathon.broadcast.DelayNotificationReceiver;
import com.travelgeeks.olahackathon.broadcast.DismissBroadcastReceiver;
import com.travelgeeks.olahackathon.data.BookingData;
import com.travelgeeks.olahackathon.ride.GridRideActivity;
import com.travelgeeks.olahackathon.service.BookCabService;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class NotificationHandler {

    public static final int ID_BOOK_LATER = 1;
    public static final int NOTIFICATION_ID = 1001;
    public static final int NOTIFICATION_BOOKING_ID = 1002;


    /**
     * Build notification for cab booking
     * @param context
     * @param data
     */
    public static void buildNotificationForBooking(Context context, BookingData data) {
        ApplicationPreference.getInstance().put(ApplicationPreference.LAST_EVENT_TIME, System.currentTimeMillis());
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        if (data.getMessage() == null) {
            builder.setContentTitle("Cab booked");
            builder.setContentText(data.getCabNumber() + " " + data.getDriverName());
        } else {
            builder.setContentTitle(data.getCode());
            builder.setContentText(data.getMessage());
        }
        builder.setSmallIcon(R.drawable.ic_directions_car_black_24dp);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, GridRideActivity.class), PendingIntent
                .FLAG_UPDATE_CURRENT));
        builder.setAutoCancel(true);
        Notification build = builder.build();
        notificationManager.notify(NOTIFICATION_BOOKING_ID, build);
    }

    /**
     * Kind of notification center.
     * @param context
     * @param notificationData
     */
    public static void buildNotification(Context context, NotificationData notificationData) {
        long time = ApplicationPreference.getInstance().get(ApplicationPreference.LAST_EVENT_TIME, 0l);
        if (System.currentTimeMillis() - time < AlarmManager.INTERVAL_HOUR * 3) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(notificationData.getTitle());
        builder.setContentText(notificationData.getContent());
        builder.setSmallIcon(R.drawable.ic_directions_car_black_24dp);
        builder.setContentIntent(PendingIntent.getActivity(context, 1, new Intent(context, GridRideActivity.class), PendingIntent
                .FLAG_UPDATE_CURRENT));
        builder.setAutoCancel(true);
        Notification build = builder.build();
        build.bigContentView = buildRemoteView(context, notificationData);
        notificationManager.notify(NOTIFICATION_ID, build);
    }


    public static void cancelNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private static RemoteViews buildRemoteView(Context context, NotificationData data) {
        RemoteViews removeWidget = new RemoteViews(context.getPackageName(), R.layout.event_notification);
        removeWidget.setTextViewText(R.id.notification_title, data.getTitle());
        removeWidget.setTextViewText(R.id.notification_content, data.getContent());
        removeWidget.setOnClickPendingIntent(R.id.notification_remind_later, bookCabLater(context, data.getId()));
        removeWidget.setOnClickPendingIntent(R.id.notification_book_fastest, bookFastestCab(context));
        removeWidget.setOnClickPendingIntent(R.id.notification_dismiss, bookingDismiss(context));
        return removeWidget;
    }

    /**
     * Start a Intent service for booking
     *
     * @param context
     * @return
     */
    private static PendingIntent bookFastestCab(Context context) {
        Intent intent = new Intent(context, BookCabService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    /**
     * Start a boardcast receiver to show notification.
     *
     * @param context
     * @return
     */
    private static PendingIntent bookCabLater(Context context, String id) {
        Intent intent = new Intent(context, DelayNotificationReceiver.class);
        intent.putExtra(DelayNotificationReceiver.REQUEST_ID, id);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, ID_BOOK_LATER, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return broadcast;
    }


    /**
     * Open activity
     *
     * @return
     */
    private static PendingIntent bookingDismiss(Context context) {
        Intent intent = new Intent(context, DismissBroadcastReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return broadcast;
    }

}
