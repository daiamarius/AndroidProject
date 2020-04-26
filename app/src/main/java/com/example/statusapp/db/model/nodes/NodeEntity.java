package com.example.statusapp.db.model.nodes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "node")
public class NodeEntity {
    @PrimaryKey
    private int nodeId;
    private String name;
    private int passing;
    private int failing;
    private int warning;
    private String host;
    private String registered;

    public NodeEntity(int nodeId, String name, int passing, int failing, int warning, String host, String registered) {
        this.nodeId = nodeId;
        this.name = name;
        this.passing = passing;
        this.failing = failing;
        this.warning = warning;
        this.host = host;
        this.registered = registered;
    }

    public int getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public int getPassing() {
        return passing;
    }

    public int getFailing() {
        return failing;
    }

    public int getWarning() {
        return warning;
    }

    public String getHost() {
        return host;
    }

    public String getRegistered() {
        return registered;
    }

    @Override
    public String toString() {
        return "NodeEntity{" +
                "nodeId=" + nodeId +
                ", name='" + name + '\'' +
                ", passing=" + passing +
                ", failing=" + failing +
                ", warning=" + warning +
                ", host='" + host + '\'' +
                ", registered='" + registered + '\'' +
                '}';
    }
}
