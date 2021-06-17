package com.example.myfirstprikorm.api;
import com.example.myfirstprikorm.data.ViewModel.Post;
import com.example.myfirstprikorm.data.ViewModel.PrikormList;
import com.example.myfirstprikorm.data.ViewModel.Product;
import com.example.myfirstprikorm.data.ViewModel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApiCallServiceMP {
//    @GET("posts")
//    Call<List<Post>> getPosts();
    @GET("/api/Products")
    Call<List<Product>> getProducts();

    @POST("/api/Products")
    Call<Product> createProduct(@Body Product product);

    @GET("/api/Users")
    Call<List<User>> getUsers();

    @POST("/api/Users")
    Call<User> createUser(@Body User user);

    @GET("/api/PrikormLists")
    Call<List<PrikormList>> getPrikormLists();

    @POST("/api/PrikormLists")
    Call<PrikormList> createPrikormList(@Body PrikormList prikormList);
}
