package com.example.statusapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.statusapp.R;
import com.example.statusapp.mvvc.ServiceViewModel;
import com.example.statusapp.mvvc.ServicesAdapter;
import com.example.statusapp.db.model.ServiceWithTags;

import java.util.List;

public class ServicesActivity extends AppCompatActivity{

    private final String TAG = "ServicesActivity";
    private RecyclerView recyclerView;
    private ServiceViewModel serviceViewModel;
    private SwipeRefreshLayout refreshLayout;

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
            }
        });

        refreshLayout = findViewById(R.id.swipe_refreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                serviceViewModel.getServicesFromAPIAndStore();
                refreshLayout.setRefreshing(false);
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
