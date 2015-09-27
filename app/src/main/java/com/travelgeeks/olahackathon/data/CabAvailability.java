package com.travelgeeks.olahackathon.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class CabAvailability {


    public static Comparator<CabAvailability> NEAREST = new Comparator<CabAvailability>() {
        @Override
        public int compare(CabAvailability lhs, CabAvailability rhs) {
            if (lhs.eta == rhs.eta) {
                return (int) (lhs.getDistanceInMeter() - rhs.getDistanceInMeter());
            }
            return lhs.eta - rhs.eta;
        }
    };


    /**
     * 10 km - 40 min
     */
    public static Comparator<CabAvailability> CHEAPEST = new Comparator<CabAvailability>() {
        @Override
        public int compare(CabAvailability lhs, CabAvailability rhs) {
            FareBreakUp lhsFareBreakUp = lhs.fareBreakUpList.get(0);
            FareBreakUp rhsFareBreakUp = rhs.fareBreakUpList.get(0);
            if (lhsFareBreakUp.calculatePrice(0, 0) == rhsFareBreakUp.calculatePrice(0, 0)) {
                if (lhs.eta == rhs.eta) {
                    return (int) (lhs.getDistanceInMeter() - rhs.getDistanceInMeter());
                }
                return lhs.eta - rhs.eta;
            }
            return (int) (lhsFareBreakUp.calculatePrice(0, 0) - rhsFareBreakUp.calculatePrice(0, 0));
        }
    };

    /**
     * Cab types
     */
    public static final String CAB_MINI = "Mini";
    public static final String CAB_SEDAN = "Sedan";

    /**
     * Different units.
     */
    public static final String TIME_MINUTE = "minute";
    public static final String DISTANCE_KM = "km";
    public static final String DISTANCE_METER = "meter";

    public static class FareBreakUp {

        @SerializedName("type")
        public String type;
        @SerializedName("minimum_distance")
        public int minDistance;
        @SerializedName("minimum_time")
        public int minTime;
        @SerializedName("base_fare")
        public double baseFare;
        @SerializedName("cost_per_distance")
        public int costPerDistance;
        @SerializedName("waiting_cost_per_minute")
        public int waitingCostPerMinute;
        @SerializedName("ride_cost_per_minute")
        public int rideCostPerMinute;


        protected double calculatePrice(int km, int min) {
            return baseFare;
        }

    }

    @SerializedName("id")
    public String id;
    @SerializedName("display_name")
    public String displayName;
    @SerializedName("currency")
    public String currency;


    @SerializedName("distance")
    public float distance;
    @SerializedName("distance_unit")
    public String distanceUnit;

    @SerializedName("eta")
    public int eta;
    @SerializedName("time_unit")
    public String timeUnit;


    @SerializedName("image")
    public String imageUrl;

    @SerializedName("fare_breakup")
    public List<FareBreakUp> fareBreakUpList;

    private float getDistanceInMeter() {
        if (distanceUnit.equalsIgnoreCase(DISTANCE_KM)) {
            return distance * 1000;
        } else {
            return distance;
        }
    }

    public static List<CabAvailability> getStaticData() {

        FareBreakUp fareBreakUp = new FareBreakUp();
        fareBreakUp.baseFare = 100;

        FareBreakUp fareBreakUp1 = new FareBreakUp();
        fareBreakUp.baseFare = 120;

        FareBreakUp fareBreakUp2 = new FareBreakUp();
        fareBreakUp.baseFare = 140;

        FareBreakUp fareBreakUp3 = new FareBreakUp();
        fareBreakUp.baseFare = 160;

        FareBreakUp fareBreakUp4 = new FareBreakUp();
        fareBreakUp.baseFare = 180;

        FareBreakUp fareBreakUp5 = new FareBreakUp();
        fareBreakUp.baseFare = 200;


        List<CabAvailability> list = new ArrayList<>();
        CabAvailability cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_MINI;
        cabAvailability.distance = 0.5f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 10;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp);
        list.add(cabAvailability);


        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_MINI;
        cabAvailability.distance = 0.8f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 19;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp1);
        list.add(cabAvailability);



        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_MINI;
        cabAvailability.distance = 1.2f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 3;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp2);
        list.add(cabAvailability);



        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_MINI;
        cabAvailability.distance = 1.5f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 30;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp3);
        list.add(cabAvailability);




        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 1f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 5;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp4);
        list.add(cabAvailability);



        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 0.25f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 2;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp5);
        list.add(cabAvailability);



        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 0.75f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 12;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp5);
        list.add(cabAvailability);



        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 0.5f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 10;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp4);
        list.add(cabAvailability);


        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 0.5f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 10;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp4);
        list.add(cabAvailability);


        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 2.5f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 30;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp3);
        list.add(cabAvailability);

        cabAvailability = new CabAvailability();
        cabAvailability.displayName = CAB_SEDAN;
        cabAvailability.distance = 6.5f;
        cabAvailability.distanceUnit = DISTANCE_KM;
        cabAvailability.eta = 60;
        cabAvailability.timeUnit = TIME_MINUTE;
        cabAvailability.imageUrl = "http://d1foexe15giopy.cloudfront.net/mini.png";
        cabAvailability.fareBreakUpList = Arrays.asList(fareBreakUp2);
        list.add(cabAvailability);

        return list;
    }

}
