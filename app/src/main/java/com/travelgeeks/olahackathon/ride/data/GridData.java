package com.travelgeeks.olahackathon.ride.data;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class GridData {

    private String imageUrl;
    private String name;
    private String price;

    public GridData(String name, String imageUrl, String price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
