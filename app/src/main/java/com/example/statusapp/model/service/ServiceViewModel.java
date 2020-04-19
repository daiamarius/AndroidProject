package com.example.statusapp.model.service;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.statusapp.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class ServiceViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Service>> allServices;

    /**
     * Constructor
     */
    public ServiceViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allServices = repository.getAllServices();
    }

    /**
     * Region getter and setter
     */
    public LiveData<List<Service>> getAllServices() {
        return allServices;
    }

    public void setAllServices(LiveData<List<Service>> allServices) {
        this.allServices = allServices;
    }

    /**
     * Region methods
     */

    public void insert(Service s){
        repository.insert(s);
    }

    public void insertMultipleServices(ArrayList<Service> services){
        repository.insertMultipleServices(services);
    }

    public void delete(Service s){
        repository.delete(s);
    }

    public void update(Service s){
        repository.update(s);
    }
}
