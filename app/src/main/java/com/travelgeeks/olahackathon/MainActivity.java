package com.travelgeeks.olahackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.ride.GridRideActivity;
import com.travelgeeks.olahackathon.service.LocationService;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.Logger;
import com.travelgeeks.olahackathon.utilities.SmsParser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private View title;
    private View btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        btn = findViewById(R.id.btn2);
        btn.setOnClickListener(this);
        startService(new Intent(this, LocationService.class));

        new Thread() {
            @Override
            public void run() {
                super.run();
                if (ApplicationPreference.getInstance().get(ApplicationPreference.GCM, null) == null) {
                    registerGcm();
                }
            }
        }.start();
    }

    private void registerGcm() {
        try {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            String id = gcm.register(Constant.SENDER_ID);
            ApplicationPreference.getInstance().put(ApplicationPreference.GCM, id);
            Logger.d("Gcm id: " + id);
        } catch (IOException e) {
            Logger.ex(e);
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn2:
                startActivity(new Intent(this, GridRideActivity.class));
                break;
        }
    }
}
