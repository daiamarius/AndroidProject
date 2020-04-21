package com.example.statusapp.db.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ServiceWithTags {
    @Embedded private ServiceEntity service;
    @Relation(
            parentColumn = "serviceId",
            entityColumn = "userTagId",
            associateBy = @Junction(ServiceTagCrossRef.class)
    )
    private List<UserTagEntity> tags;

    public ServiceWithTags(ServiceEntity service, List<UserTagEntity> tags) {
        this.service = service;
        this.tags = tags;
    }

    public ServiceEntity getService() {
        return service;
    }

    public List<UserTagEntity> getTags() {
        return tags;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}
