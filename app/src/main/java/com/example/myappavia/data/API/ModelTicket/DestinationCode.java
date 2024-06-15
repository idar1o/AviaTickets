package com.example.myappavia.data.API.ModelTicket;

import com.google.gson.annotations.SerializedName;

public class DestinationCode {
    @SerializedName("iata")
    private String iata;
    @SerializedName("name")
    private String name;
    public DestinationCode(String iata, String name) {
        this.iata = iata;
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getNameDestination() {
        return name;
    }

    public void setNameDestination(String name) {
        this.name = name;
    }
}

