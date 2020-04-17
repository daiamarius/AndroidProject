package com.example.statusapp;

import com.example.statusapp.model.node.Nodes;
import com.example.statusapp.model.service.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface StatusappAPI {
    @Headers("Content-Type: application/json")
    @GET("services")
    Call<Services> getServices();

    @Headers("Content-Type: application/json")
    @GET("nodes")
    Call<Nodes> getNodes();
}
