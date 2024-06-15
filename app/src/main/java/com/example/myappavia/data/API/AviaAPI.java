package com.example.myappavia.data.API;

import com.example.myappavia.data.API.ModelPlace.SearchedPlace;
import com.example.myappavia.data.API.ModelTicket.GetNameCity;
import com.example.myappavia.data.API.ModelTicket.ResponseFlightModel;
import com.example.myappavia.data.API.ModelTicket.ResponseFlightModelReturn;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AviaAPI {
    @GET("/places2?locale=ru&types[]=country&types[]=city")
    Call<ArrayList<SearchedPlace>> searchPlaces(@Query("term") String searchword);

    @GET("/aviasales/v3/prices_for_dates?cy=rub&limit=30&page=1&one_way=false&direct=true&token=403d10798197e8fd6f84589167482e32")
    Call<ResponseFlightModel> foundedTicketsDeparture(@Query("origin") String origin, @Query("destination") String destination, @Query("departure_at") String departure_at, @Query("sorting") String sorting);

    @GET("/aviasales/v3/prices_for_dates?cy=rub&limit=30&page=1&one_way=false&token=403d10798197e8fd6f84589167482e32")
    Call<ResponseFlightModel> foundedTicketsReturn(@Query("origin") String origin, @Query("destination") String destination, @Query("departure_at") String departure_at, @Query("return_at") String return_at, @Query("sorting") String sorting);

    @GET("/data/ru/airports.json")
    Call<ArrayList<GetNameCity>> getNameCityWithIATA();


}

