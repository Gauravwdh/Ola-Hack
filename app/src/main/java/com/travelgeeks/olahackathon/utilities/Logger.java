package com.travelgeeks.olahackathon.utilities;

import android.util.Log;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class Logger {

    private static final String TAG = Logger.class.getName();

    public static void d(Object object) {
        d(TAG, object);
    }

    public static void d(String tag, Object object) {
        Log.d(tag, "" + object);
    }

    public static void err(Object object) {
        err(TAG, object);
    }

    public static void err(String tag, Object object) {
        Log.e(tag, "" + object);
    }

    public static void ex(Object object) {
        ex(TAG, object);
    }

    public static void ex(String tag, Object object) {
        Log.d(tag, "" + object);
    }
}
