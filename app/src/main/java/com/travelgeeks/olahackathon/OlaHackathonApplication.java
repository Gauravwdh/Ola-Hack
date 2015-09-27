package com.travelgeeks.olahackathon;

import android.app.Application;

import com.travelgeeks.olahackathon.utilities.ApiCallHandler;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.GsonHandler;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class OlaHackathonApplication extends Application {

    private static OlaHackathonApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }


    public static OlaHackathonApplication getInstance() {
        return instance;
    }

    private void initialize() {
        instance = this;
        ApiCallHandler.init(this);
        GsonHandler.getInstance();
        ApplicationPreference.init(this);
    }

}