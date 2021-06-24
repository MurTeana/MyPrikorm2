package com.example.myfirstprikorm.api.login;

import com.example.myfirstprikorm.api.login.LoginRequest;
import com.example.myfirstprikorm.api.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/api/Users/authenticate")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
