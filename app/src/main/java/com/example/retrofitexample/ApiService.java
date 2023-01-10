package com.example.retrofitexample;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //API Link: http://http://167.99.80.221/api/v1/driver/get-time-availability?day=monday
    Interceptor interceptor = chain -> {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Authorization", "$2y$10$XU3bOv8Ej04v15y8Zf4x6u3uoBR6ijnSnkpZ7yDYIxP9eho2pFVUK");
        builder.addHeader("Version", "4.0");
        return chain.proceed(builder.build());
    };

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);


    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://167.99.80.221/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

    @GET("driver/get-time-availability")
    Call<DataObject> callApi(@Query("day") String day);

    ApiService apiService2 = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

    // https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<List<DataObject>> getListData();
}
