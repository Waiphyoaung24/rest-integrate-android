package com.example.testrestapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface Api {
    String BASE_URL = "http://10.0.2.2:8080/api/employee/";
    @GET("list")
    Call<List<Employee>> getAll();


}
