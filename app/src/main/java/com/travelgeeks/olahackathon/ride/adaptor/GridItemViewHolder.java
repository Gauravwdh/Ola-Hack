package com.travelgeeks.olahackathon.ride.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travelgeeks.olahackathon.OlaHackathonApplication;
import com.travelgeeks.olahackathon.R;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.ride.data.GridData;
import com.travelgeeks.olahackathon.utilities.Util;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GridItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView tvName;
    private TextView tvDistance;
    private TextView tvEta;
    public View layout;
    public Button btnBook;


    public GridItemViewHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.img_ride_type);
        tvName = (TextView) view.findViewById(R.id.tv_ride_name);
        tvDistance = (TextView) view.findViewById(R.id.tv_ride_distance);
        tvEta = (TextView) view.findViewById(R.id.tv_ride_eta);
        layout = view.findViewById(R.id.layout);
        btnBook = (Button) view.findViewById(R.id.btn_ride_book);
    }

    public void populate(CabAvailability data) {
        Picasso.with(OlaHackathonApplication.getInstance()).load(data.imageUrl).into(imageView);
        tvName.setText(data.displayName);
        tvDistance.setText("Distance: " + data.distance + " " + data.distanceUnit);
        tvEta.setText("Eta: " + data.eta + " " + data.timeUnit);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
