package com.travelgeeks.olahackathon.ride;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.travelgeeks.olahackathon.BaseActivity;
import com.travelgeeks.olahackathon.Constant;
import com.travelgeeks.olahackathon.R;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.data.CabAvailabilityResponse;
import com.travelgeeks.olahackathon.ride.adaptor.GridItemAdaptor;
import com.travelgeeks.olahackathon.ride.data.GridData;
import com.travelgeeks.olahackathon.utilities.ApiCallHandler;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.CustomRequest;
import com.travelgeeks.olahackathon.utilities.Logger;
import com.travelgeeks.olahackathon.utilities.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GridRideActivity extends BaseActivity {

    private static final String TAG = GridRideActivity.class.getName();

    private Response.Listener<CabAvailabilityResponse> listener = new Response.Listener<CabAvailabilityResponse>() {

        @Override
        public void onResponse(CabAvailabilityResponse response) {
//            recyclerView.setAdapter(new GridItemAdaptor(GridRideActivity.this, response.getList()));

        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Logger.d(error);
            Util.showToast(GridRideActivity.this, "Error occured: " + error);
        }
    };

    private RecyclerView recyclerView;
    private GridItemAdaptor gridItemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_ride);
        recyclerView = (RecyclerView) findViewById(R.id.grid_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        List<CabAvailability> staticData = CabAvailability.getStaticData();
        Collections.sort(staticData, CabAvailability.NEAREST);
        gridItemAdaptor = new GridItemAdaptor(GridRideActivity.this, staticData);
        recyclerView.setAdapter(gridItemAdaptor);

        double lat = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LATITUDE, null));
        double lon = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LONGITUDE, null));
//        double lat = 12.9328261;
//        double lon = 77.602766;
        String api = Constant.Api.getAvailabilityApiUrl(lat, lon);
        CustomRequest<CabAvailabilityResponse> request = new CustomRequest<CabAvailabilityResponse>(Request.Method.GET, api,
                CabAvailabilityResponse.class, listener, errorListener);
        ApiCallHandler.getApiCallHandler().addRequest(TAG, request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.grid_ride_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fastest:
                gridItemAdaptor.sortByNearest();
                break;
            case R.id.menu_cheapest:
                gridItemAdaptor.sortByCheapest();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
