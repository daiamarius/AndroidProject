package com.example.statusapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.example.statusapp.model.service.Service;
import com.example.statusapp.model.service.Services;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicesActivity extends AppCompatActivity{

    private final String TAG = "ServicesActivity";
    private ArrayList<Service> services = new ArrayList<Service>();

    private static final String BASE_URL = "http://192.168.0.105:5000/api/";

    private ServicesAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        getServices();
    }

    private void getServices(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatusappAPI api = retrofit.create(StatusappAPI.class);
        Call<Services> call = api.getServices();

        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Log.d(TAG, "onResponse: Success getting data"+response.toString() );
                Log.d(TAG, "onResponse: Body" + response.body().toString());

                Services s = response.body();
                if(!s.getMessage().equals("ok"))
                    Log.e(TAG, "onResponse: API response not ok");
                else {
                    services.clear();
                    services.addAll(s.getServices());
                    Log.d(TAG, "onResponse: Services" + services.toString());
                    refreshRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed getting data" + t.getMessage() );
            }
        });
    }


    /**
     * Recycler view
     */
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServicesAdapter(this,services);
        recyclerView.setAdapter(adapter);
    }

    private void refreshRecyclerView(){
        Log.d(TAG, "refreshRecyclerView: servicel:"+services.toString());
        adapter.notifyDataSetChanged();
    }
}
