package com.example.statusapp.db.model.nodes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;


@Entity(tableName = "node_tag_join",
        primaryKeys = {"nodeId","techTagId"},
        foreignKeys = {
                @ForeignKey(entity = NodeEntity.class,
                        parentColumns = "nodeId",
                        childColumns = "nodeId"),
                @ForeignKey(entity = TechTagEntity.class,
                        parentColumns = "techTagId",
                        childColumns = "techTagId")
        })
public class NodeTagCrossRef {
    private int nodeId;
    private int techTagId;

    public NodeTagCrossRef(int nodeId, int techTagId) {
        this.nodeId = nodeId;
        this.techTagId = techTagId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getTechTagId() {
        return techTagId;
    }

    public void setTechTagId(int techTagId) {
        this.techTagId = techTagId;
    }

    @Override
    public String toString() {
        return "NodeTagCrossRef{" +
                "nodeId=" + nodeId +
                ", techTagId=" + techTagId +
                '}';
    }
}
