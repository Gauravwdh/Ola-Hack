package com.travelgeeks.olahackathon.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class CabAvailabilityResponse {


    @SerializedName("categories")
    private List<CabAvailability> list;

    public List<CabAvailability> getList() {
        return list;
    }
}
