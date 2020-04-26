package com.example.statusapp.API;

import com.example.statusapp.API.models.login.User;
import com.example.statusapp.API.models.node.NodeResponse;
import com.example.statusapp.API.models.service.ServiceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface StatusappAPI {
    @Headers("Content-Type: application/json")
    @GET("services")
    Call<ServiceResponse> getServices();

    @Headers("Content-Type: application/json")
    @GET("nodes")
    Call<NodeResponse> getNodes();

    @POST("login")
    Call<User> login(@Header("Authorization") String header);

    @Headers("Content-Type: application/json")
    @GET("all")
    Call<ServiceResponse> getAllWithToken(@Header("x-access-token") String authToken);
}
