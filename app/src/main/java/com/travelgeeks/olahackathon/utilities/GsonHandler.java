package com.travelgeeks.olahackathon.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GsonHandler {

    private Gson gson;

    private static GsonHandler gsonHandler;

    private GsonHandler() {
        GsonBuilder gsonBuild = new GsonBuilder();
        gson = gsonBuild.create();
    }

    public static GsonHandler getInstance() {
        if (gsonHandler == null) {
            gsonHandler = new GsonHandler();
        }
        return gsonHandler;
    }


    public <T> T toObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public <T> String toJson(T t) {
        return gson.toJson(t);
    }

}
