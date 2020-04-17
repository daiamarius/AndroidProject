package com.example.statusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.statusapp.model.service.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = ".MainActivity";
    private static final String BASE_URL = "http://192.168.0.105:5000/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getServices(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatusappAPI api = retrofit.create(StatusappAPI.class);
        Call<Services> call = api.getServices();

        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Log.d(TAG, "onFailure: Success getting data"+response.toString() );
                Toast.makeText(MainActivity.this,"Successful getting services",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: Body" + response.body().toString());
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed getting data"+t.getMessage() );
                Toast.makeText(MainActivity.this,"Failed getting services",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getNodes(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StatusappAPI api = retrofit.create(StatusappAPI.class);
        Call<Services> call = api.getServices();

        call.enqueue(new Callback<Services>() {
            @Override
            public void onResponse(Call<Services> call, Response<Services> response) {
                Log.d(TAG, "onFailure: Success getting data"+response.toString() );
                Toast.makeText(MainActivity.this,"Successful getting services",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onResponse: Body" + response.body().toString());
            }

            @Override
            public void onFailure(Call<Services> call, Throwable t) {
                Log.e(TAG, "onFailure: Failed getting data"+t.getMessage() );
                Toast.makeText(MainActivity.this,"Failed getting services",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
