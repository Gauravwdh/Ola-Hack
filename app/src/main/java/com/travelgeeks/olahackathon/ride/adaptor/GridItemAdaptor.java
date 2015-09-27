package com.travelgeeks.olahackathon.ride.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.travelgeeks.olahackathon.OlaHackathonApplication;
import com.travelgeeks.olahackathon.R;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.ride.data.GridData;

import java.util.Collections;
import java.util.List;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GridItemAdaptor extends RecyclerView.Adapter<GridItemViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<CabAvailability> data;

    public GridItemAdaptor(Context context, List<CabAvailability> data) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        GridItemViewHolder viewHolder = new GridItemViewHolder(layoutInflater.inflate(R.layout.grid_item, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder gridItemViewHolder, int position) {
        if (position == 0) {
            gridItemViewHolder.layout.setBackgroundColor(OlaHackathonApplication.getInstance().getResources().getColor(R.color.green));
        } else if (position == getItemCount() - 1) {
            gridItemViewHolder.layout.setBackgroundColor(OlaHackathonApplication.getInstance().getResources().getColor(R.color.red));
        }else{
            gridItemViewHolder.layout.setBackgroundColor(OlaHackathonApplication.getInstance().getResources().getColor(android.R.color.white));
        }
        gridItemViewHolder.populate(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void sortByCheapest() {
        Collections.sort(data, CabAvailability.CHEAPEST);
        notifyDataSetChanged();
    }

    public void sortByNearest() {
        Collections.sort(data, CabAvailability.NEAREST);
        notifyDataSetChanged();
    }
}
