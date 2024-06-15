package com.example.myappavia.data.API.ModelTicket;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseFlightModelReturn {
    @SerializedName("success")
    String success;
    @SerializedName("data")
    ArrayList<FlightModelReturn> data;
    @SerializedName("currency")
    String currency;

    public ResponseFlightModelReturn(String success, ArrayList<FlightModelReturn> data, String currency) {
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

    public ArrayList<FlightModelReturn> getData() {
        return data;
    }

    public void setData(ArrayList<FlightModelReturn> data) {
        this.data = data;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

