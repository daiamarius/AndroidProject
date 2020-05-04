package com.example.statusapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.statusapp.API.models.service.Service;
import com.example.statusapp.R;
import com.example.statusapp.db.model.ServiceEntity;
import com.example.statusapp.db.model.ServiceWithTags;
import com.example.statusapp.mvvc.ServiceRepository;
import com.example.statusapp.mvvc.ServiceViewModel;
import com.example.statusapp.mvvc.StatisticsView;
import com.example.statusapp.mvvc.StatisticsViewModel;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    StatisticsViewModel viewModel;
    private SwipeRefreshLayout refreshLayout;
    private StatisticsView statisticsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        statisticsView = findViewById(R.id.statistics_view);

         viewModel = ViewModelProviders.of(this).get(StatisticsViewModel.class);
        if(isNetworkConnected(this)){
            viewModel.getServicesFromAPIAndStore();
        }
        else{
            Toast.makeText(this,"No network connection. Showing cached data",Toast.LENGTH_SHORT).show();
        }

        viewModel.getServices().observe(this, new Observer<List<ServiceWithTags>>() {
            @Override
            public void onChanged(List<ServiceWithTags> services) {
                List<ServiceEntity> entities = new ArrayList<>();
                for(ServiceWithTags s : services)
                    entities.add(s.getService());
                statisticsView.setServices(entities);
            }
        });

        refreshLayout = findViewById(R.id.statistics_refreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getServicesFromAPIAndStore();
                refreshLayout.setRefreshing(false);
            }
        });
    }


    private boolean isNetworkConnected(Context ctx){
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
