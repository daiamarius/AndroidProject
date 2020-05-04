package com.example.statusapp.mvvc;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.statusapp.db.StatusappDB;
import com.example.statusapp.db.model.ServiceWithTags;
import com.example.statusapp.API.StatusappAPI;
import com.example.statusapp.API.models.service.ServiceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ServiceRepository {
    private final String TAG = "ServiceRepository";

    //API
    private static final String BASE_URL = "http://192.168.0.105:5000/api/";
    //SQLite db
    private StatusappDB db;
    private Application app;

    public ServiceRepository(Application app){
        this.app = app;
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

        SharedPreferences sp = app.getSharedPreferences("sharedPref",MODE_PRIVATE);
        String token = sp.getString("token", null);

        Log.d(TAG, "ApiCallAndPutInDB: token"+token);

        //Call<ServiceResponse> call = api.getServices();
        Call<ServiceResponse> call = api.getServicesWithToken(token);

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                Log.d(TAG, "onResponse: Success getting data "+response.toString() );
                Log.d(TAG, "onResponse: Body" + response.body().toString());
                if(response.code()==200){
                    if(response.body().getMessage().equals("ok")){
                        db.deleteAllServices();
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
                Toast.makeText(app.getApplicationContext(),"Getting data failed. Displaying cached data.",Toast.LENGTH_LONG).show();
                Log.e(TAG, "onFailure: Failed getting data" + t.getMessage() );
            }
        });
    }

}
