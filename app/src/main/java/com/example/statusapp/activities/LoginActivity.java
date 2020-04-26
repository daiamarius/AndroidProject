package com.example.statusapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.statusapp.API.StatusappAPI;
import com.example.statusapp.API.models.login.Login;
import com.example.statusapp.API.models.login.User;
import com.example.statusapp.API.models.service.ServiceResponse;
import com.example.statusapp.R;
import com.example.statusapp.activities.MenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://192.168.0.105:5000/api/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    StatusappAPI api = retrofit.create(StatusappAPI.class);

    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loading = findViewById(R.id.progress_login);
    }

    public void login(View view) {
        final EditText edit_username = findViewById(R.id.username);
        final EditText edit_password = findViewById(R.id.password);
        Login login = new Login(edit_username.getText().toString(),edit_password.getText().toString());
        Call<User> call = api.login(login.toHeader());

        loading.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.body().getToken(),Toast.LENGTH_LONG).show();
                    SharedPreferences sp;
                    sp = getSharedPreferences("sharedPref",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("current_user",edit_username.getText().toString());
                    editor.putString("token",response.body().getToken());
                    editor.commit();

                    Intent it = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(it);
                    loading.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
                    loading.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Login service unavailable",Toast.LENGTH_LONG).show();
                loading.setVisibility(View.INVISIBLE);
            }
        });
    }
}
