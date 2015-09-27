package com.travelgeeks.olahackathon.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.travelgeeks.olahackathon.broadcast.RestartBroadcastReceiver;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.Logger;

import java.util.Calendar;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient
        .OnConnectionFailedListener {

    public static final String TAG = LocationService.class.getSimpleName();

    private long HIGHER_FREQUENCY_INTERVAL = 1 * 60 * 1000;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mCurrentLocation;


    @Override
    public void onCreate() {
        super.onCreate();
        buildGoogleApiClient();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "google api client connected");
        fetchLocation();
    }

    private void fetchLocation() {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        double longitude = mCurrentLocation.getLongitude();
        double latitude = mCurrentLocation.getLatitude();
        ApplicationPreference.getInstance().put(ApplicationPreference.UPDATED_LONGITUDE, String.valueOf(longitude));
        ApplicationPreference.getInstance().put(ApplicationPreference.UPDATED_LATITUDE, String.valueOf(latitude));
        setScheduledAlarm();
        Logger.d("Got Location");
    }

    private void setScheduledAlarm() {
        stopSelf();
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        PendingIntent service = PendingIntent.getService(getApplicationContext(), 100, new Intent(getApplicationContext(),
                LocationService.class), PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10 * 60 * 1000, 10 * 60 * 1000, service);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());

    }

    private void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mGoogleApiClient.connect();
        if (mGoogleApiClient.isConnected()) {
            fetchLocation();
        }
        return START_NOT_STICKY;
    }

}

