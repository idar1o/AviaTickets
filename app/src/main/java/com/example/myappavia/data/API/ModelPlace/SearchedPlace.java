package com.example.myappavia.data.API.ModelPlace;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchedPlace {
    @SerializedName("id")
    String id;
    @SerializedName("type")
    String type;
    @SerializedName("code")
    String code;
    @SerializedName("name")
    String name;
    @SerializedName("country_code")
    String country_code;
    @SerializedName("country_name")
    String country_name;
    @SerializedName("state_code")
    String state_code;
    @SerializedName("coordinates")
    Coordinates coordinates;
    @SerializedName("index_strings")
    List<String> IndStrings;
    @SerializedName("weight")
    String weight;
    @SerializedName("cases")
    Cases cases;
    @SerializedName("country_cases")
    CountryCases country_cases;
    @SerializedName("main_airport_name")
    String main_airport_name;

    public SearchedPlace(String id, String type, String code,String name, String country_code, String country_name, String state_code, Coordinates coordinates, List<String> indStrings, String weight, Cases cases, CountryCases country_cases, String main_airport_name) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.name = name;
        this.country_code = country_code;
        this.country_name = country_name;
        this.state_code = state_code;
        this.coordinates = coordinates;
        IndStrings = indStrings;
        this.weight = weight;
        this.cases = cases;
        this.country_cases = country_cases;
        this.main_airport_name = main_airport_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getIndStrings() {
        return IndStrings;
    }

    public void setIndStrings(List<String> indStrings) {
        IndStrings = indStrings;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Cases getCases() {
        return cases;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
    }

    public CountryCases getCountry_cases() {
        return country_cases;
    }

    public void setCountry_cases(CountryCases country_cases) {
        this.country_cases = country_cases;
    }

    public String getMain_airport_name() {
        return main_airport_name;
    }

    public void setMain_airport_name(String main_airport_name) {
        this.main_airport_name = main_airport_name;
    }


}
