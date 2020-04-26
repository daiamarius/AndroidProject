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
import com.example.statusapp.db.model.ServiceWithTags;
import com.example.statusapp.db.model.nodes.NodeWithTags;
import com.example.statusapp.mvvc.NodeViewModel;
import com.example.statusapp.mvvc.NodesAdapter;
import com.example.statusapp.mvvc.ServiceViewModel;
import com.example.statusapp.mvvc.ServicesAdapter;

import java.util.List;

public class NodesActivity extends AppCompatActivity {
    private final String TAG = "NodesActivity";
    private RecyclerView recyclerView;
    private NodeViewModel nodeViewModel;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodes);

        recyclerView = findViewById(R.id.recycler_view);
        nodeViewModel = ViewModelProviders.of(this).get(NodeViewModel.class);

        if(isNetworkConnected(this)){
            nodeViewModel.getNodesFromAPIAndStore();
        }
        else{
            Toast.makeText(this,"No network connection. Showing cached data",Toast.LENGTH_SHORT).show();
        }

        nodeViewModel.getNodes().observe(this, new Observer<List<NodeWithTags>>() {
            @Override
            public void onChanged(List<NodeWithTags> nodes) {
                setupAdapterNodes(nodes);
            }
        });

        refreshLayout = findViewById(R.id.swipe_refreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nodeViewModel.getNodesFromAPIAndStore();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupAdapterNodes(List<NodeWithTags> nodes){
        NodesAdapter adapter = new NodesAdapter(this,nodes);
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
