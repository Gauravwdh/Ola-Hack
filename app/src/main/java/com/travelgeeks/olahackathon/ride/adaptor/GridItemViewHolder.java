package com.travelgeeks.olahackathon.ride.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.travelgeeks.olahackathon.Constant;
import com.travelgeeks.olahackathon.OlaHackathonApplication;
import com.travelgeeks.olahackathon.R;
import com.travelgeeks.olahackathon.data.BookingData;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.notification.NotificationHandler;
import com.travelgeeks.olahackathon.ride.data.GridData;
import com.travelgeeks.olahackathon.utilities.ApiCallHandler;
import com.travelgeeks.olahackathon.utilities.ApplicationPreference;
import com.travelgeeks.olahackathon.utilities.CustomRequest;
import com.travelgeeks.olahackathon.utilities.Logger;
import com.travelgeeks.olahackathon.utilities.Util;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GridItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Response.Listener<BookingData> listener = new Response.Listener<BookingData>() {

        @Override
        public void onResponse(BookingData response) {
            Logger.d(response);
            NotificationHandler.buildNotificationForBooking(layout.getContext(), response);
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Logger.d(error);
            Toast.makeText(layout.getContext(), "Error occurred while booking", Toast.LENGTH_SHORT).show();
        }
    };

    private static final String TAG = GridItemViewHolder.class.getName();
    private TextView tvBasePrice;
    private TextView tvCostPerKm;
    private ImageView imageView;
    private TextView tvName;
    private TextView tvDistance;
    private TextView tvEta;
    public View layout;


    public GridItemViewHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.img_ride_type);
        tvName = (TextView) view.findViewById(R.id.tv_ride_name);
        tvDistance = (TextView) view.findViewById(R.id.tv_ride_distance);
        tvEta = (TextView) view.findViewById(R.id.tv_ride_eta);
        layout = view.findViewById(R.id.layout);
        tvBasePrice = (TextView) view.findViewById(R.id.tv_ride_base_price);
        tvCostPerKm = (TextView) view.findViewById(R.id.tv_ride_other);
        view.setOnClickListener(this);
    }

    public void populate(CabAvailability data) {
        Picasso.with(OlaHackathonApplication.getInstance()).load(data.imageUrl).into(imageView);
        tvName.setText(data.displayName);
        tvDistance.setText("Distance: " + data.distance + " " + data.distanceUnit);
        tvEta.setText("Eta: " + data.eta + " " + data.timeUnit);
        if (data.fareBreakUpList == null || data.fareBreakUpList.size() == 0) {
            tvBasePrice.setText("Base Price: N/A");
            tvCostPerKm.setText("Cost/km: N/A");
        } else {
            CabAvailability.FareBreakUp fareBreakUp = data.fareBreakUpList.get(0);
            tvBasePrice.setText("Base Price: " + fareBreakUp.baseFare);
            tvCostPerKm.setText("Cost/km: " + fareBreakUp.costPerDistance);
        }

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(layout.getContext(), "Booking cab for u", Toast.LENGTH_SHORT).show();
        double lat = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LATITUDE, null));
        double lon = Double.parseDouble(ApplicationPreference.getInstance().get(ApplicationPreference.UPDATED_LONGITUDE, null));
        String api = Constant.Api.getBookingApiUrl(lat, lon, CabAvailability.CAB_SEDAN);
        CustomRequest<BookingData> request = new CustomRequest<BookingData>(Request.Method.GET, api,
                BookingData.class, listener, errorListener);
        ApiCallHandler.getApiCallHandler().addRequest(TAG, request);
    }
}
