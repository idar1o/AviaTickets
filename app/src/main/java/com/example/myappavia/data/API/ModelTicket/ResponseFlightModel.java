package com.example.myappavia.data.API.ModelTicket;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseFlightModel {
    @SerializedName("success")
    String success;
    @SerializedName("data")
    ArrayList<FlightModel> data;
    @SerializedName("currency")
    String currency;

    public ResponseFlightModel(String success, ArrayList<FlightModel> data, String currency) {
        this.success = success;
        this.data = data;
        this.currency = currency;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ArrayList<FlightModel> getData() {
        return data;
    }

    public void setData(ArrayList<FlightModel> data) {
        this.data = data;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

