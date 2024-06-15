package com.example.myappavia.data.API;

import com.example.myappavia.data.API.ModelTicket.ResponseIATAs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IATAAPI {
    @GET("/widgets_suggest_params")
    Call<ResponseIATAs> getIATAs(@Query("q") String fromto);
}
