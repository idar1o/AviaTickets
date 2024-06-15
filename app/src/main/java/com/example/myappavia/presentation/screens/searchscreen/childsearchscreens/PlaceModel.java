package com.example.myappavia.presentation.screens.searchscreen.childsearchscreens;

public class PlaceModel {
    private String city;
    private String country;
    private String code;

    public PlaceModel(String city, String country, String code) {
        this.city = city;
        this.country = country;
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
