package com.travelgeeks.olahackathon.ride.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.travelgeeks.olahackathon.OlaHackathonApplication;
import com.travelgeeks.olahackathon.R;
import com.travelgeeks.olahackathon.data.CabAvailability;
import com.travelgeeks.olahackathon.ride.data.GridData;
import com.travelgeeks.olahackathon.utilities.Util;

import java.util.Collections;
import java.util.List;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GridItemAdaptor extends RecyclerView.Adapter<GridItemViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<CabAvailability> data;
    private Context context;

    public GridItemAdaptor(Context context, List<CabAvailability> data) {
        this.context = context;
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
        gridItemViewHolder.populate(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void sortByCheapest() {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).fareBreakUpList == null || data.get(i).fareBreakUpList.size() == 0) {
                Util.showToast(context, "Can't sort data not available.");
                return;
            }
        }
        Collections.sort(data, CabAvailability.CHEAPEST);
        notifyDataSetChanged();
    }

    public void sortByNearest() {
        Collections.sort(data, CabAvailability.NEAREST);
        notifyDataSetChanged();
    }
}
