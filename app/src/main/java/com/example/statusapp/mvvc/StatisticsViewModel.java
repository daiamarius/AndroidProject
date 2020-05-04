package com.example.statusapp.mvvc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.statusapp.db.model.ServiceWithTags;

import java.util.List;

public class StatisticsViewModel extends AndroidViewModel {
    private ServiceRepository repository;

    public StatisticsViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ServiceRepository(application);
    }

    public void getServicesFromAPIAndStore(){
        repository.ApiCallAndPutInDB();
    }

    public LiveData<List<ServiceWithTags>> getServices(){
        return repository.getAllServices();
    }
}
