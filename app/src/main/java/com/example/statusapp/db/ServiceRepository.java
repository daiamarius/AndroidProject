package com.example.statusapp.db;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.statusapp.db.modelROOM.ServiceEntity;
import com.example.statusapp.db.modelROOM.ServiceWithTags;
import com.example.statusapp.db.modelROOM.UserTagEntity;
import com.example.statusapp.modelAPI.StatusappAPI;
import com.example.statusapp.modelAPI.Tag;
import com.example.statusapp.modelAPI.service.Service;
import com.example.statusapp.modelAPI.service.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRepository {
    private final String TAG = "ServiceRepository";

    //API
    private static final String BASE_URL = "http://192.168.0.105:5000/api/";
    //SQLite db
    private StatusappDB db;

    public ServiceRepository(Application app){
        db = StatusappDB.getInstance(app);
    }

    public LiveData<List<ServiceWithTags>> getAllServices(){
        return db.serviceDao().getServicesWithTags();
    }

    public void ApiCallAndPutInDB(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatusappAPI api = retrofit.create(StatusappAPI.class);
        Call<ServiceResponse> call = api.getServices();

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                Log.d(TAG, "onResponse: Success getting data "+response.toString() );
                Log.d(TAG, "onResponse: Body" + response.body().toString());
                if(response.code()==200){
                    if(response.body().getMessage().equals("ok")){
                        //TODO - DELETE FIRST
                        db.insertServices(response.body().getServices());
                        Log.d(TAG, "onResponse: ServiceResponse" + response.body().getServices().toString());
                    }
                    else
                        Log.e(TAG, "onResponse: API response not ok");
                }
                else
                    Log.e(TAG, "onResponse: message code not 200");
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed getting data" + t.getMessage() );
            }
        });
    }

}
