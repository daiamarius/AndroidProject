package com.example.statusapp.API;

import com.example.statusapp.API.models.node.NodeResponse;
import com.example.statusapp.API.models.service.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface StatusappAPI {
    @Headers("Content-Type: application/json")
    @GET("services")
    Call<ServiceResponse> getServices();

    @Headers("Content-Type: application/json")
    @GET("nodes")
    Call<NodeResponse> getNodes();
}
