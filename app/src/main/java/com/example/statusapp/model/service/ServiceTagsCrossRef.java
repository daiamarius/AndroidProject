package com.example.statusapp.model.service;

import androidx.room.Entity;

@Entity(primaryKeys = {"serviceId","userTagId"})
public class ServiceTagsCrossRef {
    private int serviceId;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserTagId() {
        return userTagId;
    }

    public void setUserTagId(int userTagId) {
        this.userTagId = userTagId;
    }

    private int userTagId;

    public ServiceTagsCrossRef(int serviceId, int userTagId) {
        this.serviceId = serviceId;
        this.userTagId = userTagId;
    }
}
