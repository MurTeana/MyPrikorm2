package com.example.myfirstprikorm.api;

import com.example.myfirstprikorm.data.ViewModel.Post;
import com.example.myfirstprikorm.data.ViewModel.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Response;

public class ApiRequestsMP {

    private Context context;

    //IApiCallServiceMP
    public IApiCallServiceMP jsonPlaceHolderApiCallServiceMyPrikorm() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:45455")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApiCallServiceMP iApiCallServiceMP = retrofit.create(IApiCallServiceMP.class);

        return iApiCallServiceMP;
    }
    // GET VIEW PRODUCTS
    public TextView AddProductList(IApiCallServiceMP iApiCallServiceMP, TextView textViewResult_) {

        Call<List<Product>> call = iApiCallServiceMP.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    textViewResult_.setText("Code: " + response.code());
                    return;
                }

                List<Product> products = response.body();

                for (Product product:products){
                    String content = "";
                    content +="ID: " + product.getId() + "\n";
                    content +="Product: " + product.getProduct() + "\n\n";

                    textViewResult_.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                textViewResult_.setText(t.getMessage());
            }
        });
        return textViewResult_;
    }
    // GET STRING PRODUCTS
    public int leng;
    public String[] result;

    public String[] AddProductListString(IApiCallServiceMP iApiCallServiceMP) {
        leng = 10;
        result = new String[leng];

        Call<List<Product>> call = iApiCallServiceMP.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Product> products = response.body();
                //int leng1 = products.size();
                int i = 0;
                for (Product product:products){
                    String content = "";
                    content = product.getProduct();
                    result[i] = content;
                    i++;
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }
    // POST STRING PRODUCTS


    //

//    //IApiCallServiceMP TEST
//    public IApiCallServiceMP jsonPlaceHolderApiCallService_() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        IApiCallServiceMP iApiCallServiceMP = retrofit.create(IApiCallServiceMP.class);
//
//        return iApiCallServiceMP;
//    }
//    // GET VIEW TEST
//    public TextView AddList(IApiCallServiceMP iApiCallServiceMP, TextView textViewResult_) {
//
//        Call<List<Post>> call = iApiCallServiceMP.getPosts();
//
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (!response.isSuccessful()){
//                    textViewResult_.setText("Code: " + response.code());
//                    return;
//                }
//
//                List<Post> posts = response.body();
//
//                for (Post post:posts){
//                    String content = "";
//                    content +="ID: " + post.getId() + "\n";
//                    content +="User ID: " + post.getUserId() + "\n";
//                    content +="Title: " + post.getTitle() + "\n";
//                    content +="Text: : " + post.getText() + "\n\n";
//
//                    textViewResult_.append(content);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                textViewResult_.setText(t.getMessage());
//            }
//        });
//
//        return textViewResult_;
//    }
}
