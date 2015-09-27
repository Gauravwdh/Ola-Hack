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
import com.travelgeeks.olahackathon.data.CabAvailabilityResponse;
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
            Util.showToast(BookCabService.this, "Error occurred while booking cab");
            Logger.d(error);
        }
    };
    private Response.Listener<CabAvailabilityResponse> availableListener = new Response.Listener<CabAvailabilityResponse>() {

        @Override
        public void onResponse(CabAvailabilityResponse response) {
            if (response.getList().size() == 0) {
                Util.showToast(BookCabService.this, "No cab available");
            }
            List<CabAvailability> list = response.getList();
            boolean isBooked = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).eta != -1) {
                    double lat = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LATITUDE, null));
                    double lon = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LONGITUDE, null));
                    String api = Constant.Api.getBookingApiUrl(lat, lon, list.get(i).displayName);
                    CustomRequest<BookingData> request = new CustomRequest<BookingData>(Request.Method.GET, api,
                            BookingData.class, listener, errorListener);
                    ApiCallHandler.getApiCallHandler().addRequest(TAG, request);
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) {
                Util.showToast(BookCabService.this, "Error occurred while booking cab");
            }
        }
    };
    ;

    public BookCabService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ApplicationPreference.getInstance().put(ApplicationPreference.LAST_EVENT_TIME, System.currentTimeMillis());
        NotificationHandler.cancelNotification(getApplicationContext());
        double lat = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LATITUDE, null));
        double lon = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LONGITUDE, null));

        String api = Constant.Api.getAvailabilityApiUrl(lat, lon);
        CustomRequest<CabAvailabilityResponse> request = new CustomRequest<CabAvailabilityResponse>(Request.Method.GET, api,
                CabAvailabilityResponse.class, availableListener, errorListener);
        ApiCallHandler.getApiCallHandler().addRequest(TAG, request);


    }
}
