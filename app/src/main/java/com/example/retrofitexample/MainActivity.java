package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiService.apiService2.getListData().enqueue(new Callback<List<DataObject>>() {
            @Override
            public void onResponse(Call<List<DataObject>> call, Response<List<DataObject>> response) {

            }

            @Override
            public void onFailure(Call<List<DataObject>> call, Throwable t) {

            }
        });
    }
}