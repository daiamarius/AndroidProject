package com.example.statusapp.modelAPI;

import com.example.statusapp.modelAPI.node.NodeResponse;
import com.example.statusapp.modelAPI.service.ServiceResponse;

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
