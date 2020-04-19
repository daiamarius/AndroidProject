package com.example.statusapp.model.service;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.statusapp.model.node.Node;
import com.example.statusapp.model.service.Service;

import java.util.List;

public class ServiceWithNodes {
    @Embedded public Service service;
    @Relation(
            parentColumn = "serviceId",
            entityColumn = "serviceCreatorId"
    )
    public List<Node> nodes;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
