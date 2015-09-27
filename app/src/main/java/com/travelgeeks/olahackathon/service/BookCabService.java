package com.travelgeeks.olahackathon.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.travelgeeks.olahackathon.Constant;
import com.travelgeeks.olahackathon.data.BookingData;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.notification.NotificationHandler;
import com.travelgeeks.olahackathon.ride.adaptor.GridItemAdaptor;
import com.travelgeeks.olahackathon.ride.data.GridData;
import com.travelgeeks.olahackathon.utilities.ApiCallHandler;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.CustomRequest;
import com.travelgeeks.olahackathon.utilities.Logger;
import com.travelgeeks.olahackathon.utilities.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class BookCabService extends IntentService {

    private static final String TAG = BookCabService.class.getName();

    private Response.Listener<BookingData> listener = new Response.Listener<BookingData>() {

        @Override
        public void onResponse(BookingData response) {
            Logger.d(response);
            NotificationHandler.buildNotificationForBooking(getApplicationContext(), response);
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Logger.d(error);
        }
    };

    public BookCabService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ApplicationPreference.getInstance().put(ApplicationPreference.LAST_EVENT_TIME, System.currentTimeMillis());
        NotificationHandler.cancelNotification(getApplicationContext());
        double lat = 12.9328261;
        double lon = 77.602766;
        String api = Constant.Api.getBookingApiUrl(lat, lon, CabAvailability.CAB_SEDAN);
        CustomRequest<BookingData> request = new CustomRequest<BookingData>(Request.Method.GET, api,
                BookingData.class, listener, errorListener);
        ApiCallHandler.getApiCallHandler().addRequest(TAG, request);

    }
}
