package com.example.myappavia.data.API.ModelTicket;

import com.google.gson.annotations.SerializedName;

public class OriginCode {
    @SerializedName("iata")
    private String iata;
    @SerializedName("name")
    private String name;


    public OriginCode(String iata, String name) {
        this.iata = iata;
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getNameOrigin() {
        return name;
    }

    public void setNameOrigin(String name) {
        this.name = name;
    }
}
