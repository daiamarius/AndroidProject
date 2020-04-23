package com.example.statusapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.statusapp.R;
import com.example.statusapp.activities.NodesActivity;
import com.example.statusapp.activities.ServicesActivity;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView services;
    private CardView nodes;
    private CardView statistics;
    private CardView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        services = findViewById(R.id.button_services);
        nodes = findViewById(R.id.button_nodes);
        statistics = findViewById(R.id.button_statistics);
        about = findViewById(R.id.button_about);

        services.setOnClickListener(this);
        nodes.setOnClickListener(this);
        about.setOnClickListener(this);
        statistics.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;

        switch (v.getId()){
            case R.id.button_services:
                i = new Intent(this, ServicesActivity.class);
                break;
            case R.id.button_nodes:
                i = new Intent(this, NodesActivity.class);
                break;
            case R.id.button_about:
                i = new Intent(this, AboutActivity.class);
                break;
            case R.id.button_statistics:
                i = new Intent(this, StatisticsActivity.class);
                break;
            default:
                break;
        }

        if(i!=null){
            startActivity(i);
        }
    }
}
