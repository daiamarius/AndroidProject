package com.example.statusapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.statusapp.db.ServiceRepository;
import com.example.statusapp.db.modelROOM.ServiceWithTags;
import java.util.List;

public class ServiceViewModel extends AndroidViewModel {
    private ServiceRepository repository;

    public ServiceViewModel(@NonNull Application application) {
        super(application);
        repository = new ServiceRepository(application);
    }

    public void getServicesFromAPIAndStore(){
        repository.ApiCallAndPutInDB();
    }

    public LiveData<List<ServiceWithTags>> getServices(){
        return repository.getAllServices();
    }
}
