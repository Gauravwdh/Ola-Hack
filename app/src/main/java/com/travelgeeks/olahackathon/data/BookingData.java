package com.travelgeeks.olahackathon.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gauravwadhwa on 27/09/15.
 */
public class BookingData {

    @SerializedName("crn")
    private String crn;
    @SerializedName("driver_name")
    private String driverName;
    @SerializedName("driver_number")
    private String driverNumber;
    @SerializedName("cab_type")
    private String cabType;
    @SerializedName("cab_number")
    private String cabNumber;
    @SerializedName("car_model")
    private String carModel;
    @SerializedName("eta")
    private int eta;
    @SerializedName("driver_lat")
    private double driverLat;
    @SerializedName("driver_lon")
    private double driverLon;
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getCrn() {
        return crn;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getCabNumber() {
        return cabNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCabType() {
        return cabType;
    }

    public String getDriverNumber() {
        return driverNumber;
    }
}
