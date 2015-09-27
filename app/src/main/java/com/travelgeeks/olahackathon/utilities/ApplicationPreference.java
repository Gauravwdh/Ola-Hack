package com.travelgeeks.olahackathon.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class ApplicationPreference {

    private static final String FILE_NAME = "ola_hack.xml";

    public static final String GCM = "gcm";
    /**
     * long value to save last time event driven. book, cancel or other
     */
    public static final String LAST_EVENT_TIME = "last_event_time";

    public static final String LAST_LOCATION_UPDATE = "last_location_update";
    public static final String UPDATED_LATITUDE = "updated_latitude";
    public static final String UPDATED_LONGITUDE = "updated_longitide";

    private static ApplicationPreference instance;

    private final SharedPreferences sharedPreferences;


    private ApplicationPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        instance = new ApplicationPreference(context);
    }

    public static ApplicationPreference getInstance() {
        return instance;
    }

    public void put(String key, int value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public int get(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void put(String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public String get(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    public void put(String key, long value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public long get(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }


}
