package com.example.statusapp.modelAPI.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceResponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("services")
    @Expose
    private ArrayList<Service> services;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "message='" + message + '\'' +
                ", services=" + services +
                '}';
    }
}
