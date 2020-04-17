package com.example.statusapp.model.node;

import com.example.statusapp.model.service.Service;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Nodes {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("nodes")
    @Expose
    private ArrayList<Node> nodes;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "message='" + message + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
