package com.example.myfirstprikorm.api;
import com.example.myfirstprikorm.data.ViewModel.Post;
import com.example.myfirstprikorm.data.ViewModel.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApiCallServiceMP {
//    @GET("posts")
//    Call<List<Post>> getPosts();

    //
    @GET("/api/Products")
    Call<List<Product>> getProducts();

    @POST("/api/Products")
    Call<Product> addProduct(@Body Product product);
}
