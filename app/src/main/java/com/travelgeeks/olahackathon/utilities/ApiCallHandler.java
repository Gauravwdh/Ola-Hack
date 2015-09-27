package com.travelgeeks.olahackathon.utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class ApiCallHandler {

    public static ApiCallHandler apiCallHandler;

    private final RequestQueue requestQueue;

    private ApiCallHandler(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static void init(Context context) {
        if (apiCallHandler != null) {
            throw new IllegalArgumentException("Can't initialize ApiCallHandler.");
        }
        apiCallHandler = new ApiCallHandler(context);
    }

    public static ApiCallHandler getApiCallHandler() {
        if (apiCallHandler == null) {
            throw new NullPointerException("Initialize ApiCallHandler first.");
        }
        return apiCallHandler;
    }

    public <T> void addRequest(String tag, Request<T> request) {
        request.setTag(tag);
        requestQueue.add(request);
    }

}
