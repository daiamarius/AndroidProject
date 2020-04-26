package com.example.statusapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.statusapp.db.model.ServiceEntity;
import com.example.statusapp.db.model.ServiceTagCrossRef;
import com.example.statusapp.db.model.ServiceWithTags;
import com.example.statusapp.db.model.UserTagEntity;
import com.example.statusapp.db.model.nodes.NodeEntity;
import com.example.statusapp.db.model.nodes.NodeTagCrossRef;
import com.example.statusapp.db.model.nodes.NodeWithTags;
import com.example.statusapp.db.model.nodes.TechTagEntity;

import java.util.List;

@Dao
public interface NodeDao {
    /**
     * INSERTS
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNodes(NodeEntity... e);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTechTags(TechTagEntity... e);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNodeTagCrossRef(NodeTagCrossRef... ref);

    /**
     * QUERY
     */
    @Query("SELECT * FROM node")
    LiveData<List<NodeEntity>> getNodes();

    @Query("SELECT * FROM techtag")
    LiveData<List<TechTagEntity>> getTechTags();

    @Transaction
    @Query("SELECT * FROM node")
    LiveData<List<NodeWithTags>> getNodesWithTags();



    /**
     * Delete
     */

    @Delete
    void deleteServices(NodeEntity... e);

    @Delete
    void deleteUserTags(TechTagEntity... e);

    @Query("DELETE FROM node_tag_join WHERE nodeId = :nodeId")
    void deleteNodeTagCrossRefs(int nodeId);

    @Query("DELETE FROM node")
    void deleteAllNodes();

    @Query("DELETE FROM techtag")
    void deleteAllTechTags();

    @Query("DELETE FROM node_tag_join")
    void deleteAllNodeTagCrossRefs();

    /**
     * Update
     */
    @Update
    void update(NodeEntity... e);
}
