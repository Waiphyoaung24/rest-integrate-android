package com.example.testrestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFetch = findViewById(R.id.btn_fetch);

        Toast.makeText(this, "starting", Toast.LENGTH_SHORT).show();


        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                            fetchData();


                    }
                }).start();


            }
        });

    }

    private void fetchData() {
        Call<List<Employee>> call = RetrofitClient.getInstance().getMyApi().getAll();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> eList = response.body();

                Toast.makeText(MainActivity.this, String.valueOf(eList.size()), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < eList.size(); i++) {
                    Log.e("name", eList.get(i).getName());
                }

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}


