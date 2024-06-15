package com.example.myappavia.data.API.ModelTicket;

import com.google.gson.annotations.SerializedName;

public class FlightModelReturn {
    @SerializedName("origin")
    String origin;
    @SerializedName("destination")
    String destination;
    @SerializedName("origin_airport")
    String origin_airport;
    @SerializedName("destination_airport")
    String destination_airport;
    @SerializedName("price")
    String price;
    @SerializedName("airline")
    String airline;
    @SerializedName("flight_number")
    String flight_number;
    @SerializedName("departure_at")
    String departure_at;
    @SerializedName("return_at")
    String return_at;
    @SerializedName("transfers")
    String transfers;
    @SerializedName("return_transfers")
    String return_transfers;
    @SerializedName("duration")
    String duration;
    @SerializedName("duration_to")
    String duration_to;
    @SerializedName("duration_back")
    String duration_back;
    @SerializedName("link")
    String link;

    public FlightModelReturn(String origin, String destination, String origin_airport, String destination_airport, String price, String airline, String flight_number, String departure_at, String return_at, String transfers, String return_transfers, String duration, String duration_to, String duration_back) {
        this.origin = origin;
        this.destination = destination;
        this.origin_airport = origin_airport;
        this.destination_airport = destination_airport;
        this.price = price;
        this.airline = airline;
        this.flight_number = flight_number;
        this.departure_at = departure_at;
        this.return_at = return_at;
        this.transfers = transfers;
        this.return_transfers = return_transfers;
        this.duration = duration;
        this.duration_to = duration_to;
        this.duration_back = duration_back;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin_airport() {
        return origin_airport;
    }

    public void setOrigin_airport(String origin_airport) {
        this.origin_airport = origin_airport;
    }

    public String getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(String destination_airport) {
        this.destination_airport = destination_airport;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getDeparture_at() {
        return departure_at;
    }

    public void setDeparture_at(String departure_at) {
        this.departure_at = departure_at;
    }

    public String getReturn_at() {
        return return_at;
    }

    public void setReturn_at(String return_at) {
        this.return_at = return_at;
    }

    public String getTransfers() {
        return transfers;
    }

    public void setTransfers(String transfers) {
        this.transfers = transfers;
    }

    public String getReturn_transfers() {
        return return_transfers;
    }

    public void setReturn_transfers(String return_transfers) {
        this.return_transfers = return_transfers;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration_to() {
        return duration_to;
    }

    public void setDuration_to(String duration_to) {
        this.duration_to = duration_to;
    }

    public String getDuration_back() {
        return duration_back;
    }

    public void setDuration_back(String duration_back) {
        this.duration_back = duration_back;
    }
}
