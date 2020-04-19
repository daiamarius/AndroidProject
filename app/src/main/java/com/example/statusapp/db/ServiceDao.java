package com.example.statusapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.statusapp.db.modelROOM.ServiceEntity;
import com.example.statusapp.db.modelROOM.ServiceTagCrossRef;
import com.example.statusapp.db.modelROOM.ServiceWithTags;
import com.example.statusapp.db.modelROOM.UserTagEntity;

import java.util.List;

@Dao
public interface ServiceDao {
    /**
     * INSERTS
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertServices(ServiceEntity... e);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserTags(UserTagEntity... e);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertServiceTagCrossRef(ServiceTagCrossRef... ref);

    /**
     * QUERY
     */
    @Query("SELECT * FROM service")
    LiveData<List<ServiceEntity>> getServices();

    @Query("SELECT * FROM usertag")
    LiveData<List<UserTagEntity>> getUserTags();

    @Transaction
    @Query("SELECT * FROM service")
    LiveData<List<ServiceWithTags>> getServicesWithTags();



    /**
     * Delete
     */

    @Delete
    void deleteServices(ServiceEntity... e);

    @Delete
    void deleteUserTags(UserTagEntity... e);

    @Query("DELETE FROM service_tag_join WHERE serviceId = :serviceId")
    void deleteServiceTagCrossRefs(int serviceId);


    /**
     * Update
     */
    @Update
    void update(ServiceEntity... e);

}
