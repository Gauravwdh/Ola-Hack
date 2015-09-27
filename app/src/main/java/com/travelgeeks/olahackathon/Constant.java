package com.travelgeeks.olahackathon;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class Constant {


    public static final String SENDER_ID = "274819889838";

    public static final class Api {

        private static final String QUERY = "?";
        private static final String SEPRATOR = "&";
        private static final String ATTR_LAT = "pickup_lat=";
        private static final String ATTR_LON = "pickup_lng=";
        private static final String ATTR_CATEGORY = "category=";

        //        http://sandbox-t.olacabs.com/v1/products?pickup_lat=12.9491416&pickup_lng=77.64298
        private static final String HOST_NAME = " http://sandbox-t.olacabs.com";
        private static final String AVAILABILITY_API = HOST_NAME + "/v1/products";
        private static final String BOOKING_API = HOST_NAME + "/v1/bookings/create";


        public static String getAvailabilityApiUrl(double lat, double lon) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(AVAILABILITY_API).append(QUERY);
            buffer.append(ATTR_LAT).append(lat);
            buffer.append(SEPRATOR);
            buffer.append(ATTR_LON).append(lon);
            return buffer.toString();
        }


        //http://sandbox-t.olacabs.com/v1/bookings/create?pickup_lat=12.9490936&pickup_lng=77.6443056&pickup_mode=NOW&category=sedan
        public static String getBookingApiUrl(double lat, double lon, String category) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(BOOKING_API).append(QUERY);
            buffer.append(ATTR_LAT).append(lat);
            buffer.append(SEPRATOR);
            buffer.append(ATTR_LON).append(lon);
            buffer.append("&pickup_mode=NOW");
            buffer.append(SEPRATOR);
            buffer.append(ATTR_CATEGORY).append(category);
            return buffer.toString();
        }

    }
}
