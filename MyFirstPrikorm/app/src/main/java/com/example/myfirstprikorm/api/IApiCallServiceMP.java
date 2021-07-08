package com.example.myfirstprikorm.api;
import com.example.myfirstprikorm.model.Meal;
import com.example.myfirstprikorm.model.PrikormList;
import com.example.myfirstprikorm.model.Product;
import com.example.myfirstprikorm.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiCallServiceMP {

    @GET("/api/Products")
    Call<List<Product>> getProducts();

    @POST("/api/Products")
    Call<Product> createProduct(@Body Product product);

    @GET("/api/Users")
    Call<List<User>> getUsers();

    @GET("/api/Users/{id}")
    Call<List<User>> getUsersById(@Path("id") int IdUser);

    @GET("/api/Users")
    Call<List<User>> getUsersByUsername(@Query("username") String username);

    @POST("/api/Users")
    Call<User> createUser(@Body User user);

    @PUT("/api/Users/put/{id}")
    Call<User> updateUser(@Path("id") int IdUser, @Body User user);

    @GET("/api/PrikormLists")
    Call<List<PrikormList>> getPrikormLists();

    @POST("/api/PrikormLists")
    Call<PrikormList> createPrikormList(@Body PrikormList prikormList);

    @GET("/api/PrikormLists/x/{userId}")
    Call<List<PrikormList>> getPrikormListsByUserId (@Path("userId") int userId);

    @GET("/api/Meals")
    Call<List<Meal>> getMeals();
}
