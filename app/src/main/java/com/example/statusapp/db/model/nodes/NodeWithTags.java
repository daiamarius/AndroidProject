package com.example.statusapp.db.model.nodes;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class NodeWithTags {
    @Embedded
    private NodeEntity node;
    @Relation(
            parentColumn = "nodeId",
            entityColumn = "techTagId",
            associateBy = @Junction(NodeTagCrossRef.class)
    )
    private List<TechTagEntity> tags;

    public NodeWithTags(NodeEntity node, List<TechTagEntity> tags) {
        this.node = node;
        this.tags = tags;
    }

    public NodeEntity getNode() {
        return node;
    }

    public void setNode(NodeEntity node) {
        this.node = node;
    }

    public List<TechTagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TechTagEntity> tags) {
        this.tags = tags;
    }
}
