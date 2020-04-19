package com.example.statusapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.statusapp.R;
import com.example.statusapp.ServiceViewModel;
import com.example.statusapp.adapters.ServicesAdapter;
import com.example.statusapp.db.modelROOM.ServiceWithTags;
import com.example.statusapp.modelAPI.StatusappAPI;
import com.example.statusapp.modelAPI.service.Service;
import com.example.statusapp.modelAPI.service.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicesActivity extends AppCompatActivity{

    private final String TAG = "ServicesActivity";
    private RecyclerView recyclerView;
    private ServiceViewModel serviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        serviceViewModel = ViewModelProviders.of(this).get(ServiceViewModel.class);

        if(isNetworkConnected(this)){
            serviceViewModel.getServicesFromAPIAndStore();
        }
        else{
            Toast.makeText(this,"No network connection. Showing cached data",Toast.LENGTH_SHORT).show();
        }

        serviceViewModel.getServices().observe(this, new Observer<List<ServiceWithTags>>() {
            @Override
            public void onChanged(List<ServiceWithTags> services) {
                setupAdapterService(services);
                Toast.makeText(getApplicationContext(),"on changed",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupAdapterService(List<ServiceWithTags> services){
        ServicesAdapter adapter = new ServicesAdapter(this,services);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }


    private boolean isNetworkConnected(Context ctx){
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
