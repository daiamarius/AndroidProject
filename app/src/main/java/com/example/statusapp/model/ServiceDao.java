package com.example.statusapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.statusapp.model.service.Service;
import com.example.statusapp.model.service.ServiceTagsCrossRef;
import com.example.statusapp.model.service.ServiceWithTags;
import com.example.statusapp.model.service.UserTag;

import java.util.ArrayList;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@androidx.room.Dao
public interface ServiceDao {

    @Insert(onConflict = REPLACE)
    void insertService(Service s);

    @Insert(onConflict = REPLACE)
    void insertTag(UserTag tag);

    @Insert(onConflict =  REPLACE)
    void insertServiceTagCrossRef(ServiceTagsCrossRef ref);


    @Update
    void update(Service s);

    @Delete
    void delete(Service s);

    @Query("DELETE FROM services")
    void deleteAllServices();

    @Query("SELECT * FROM services")
    LiveData<List<Service>> getServices();

    @Transaction
    @Query("SELECT * FROM services")
    LiveData<List<ServiceWithTags>> getServicesWithTags();

}
