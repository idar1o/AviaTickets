package com.example.myappavia.data.API.ModelPlace;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("lon")
    private String lon;
    @SerializedName("lat")
    private String lat;

    public Coordinates(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
