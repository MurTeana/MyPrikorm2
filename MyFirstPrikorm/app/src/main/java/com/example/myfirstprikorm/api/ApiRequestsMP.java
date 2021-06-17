package com.example.myfirstprikorm.api;

import com.example.myfirstprikorm.data.ViewModel.PrikormList;
import com.example.myfirstprikorm.data.ViewModel.Product;
import com.example.myfirstprikorm.data.ViewModel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Response;

public class ApiRequestsMP {

    private Context context;

    //IApiCallServiceMP
    public IApiCallServiceMP _ApiCallServiceMP() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:45455")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApiCallServiceMP iApiCallServiceMP = retrofit.create(IApiCallServiceMP.class);

        return iApiCallServiceMP;
    }
    // GET STRING PRODUCTS
    public String[] GETProducts(IApiCallServiceMP iApiCallServiceMP) {
        String[] result;
        int leng = 100;
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
    public void POSTProduct(IApiCallServiceMP iApiCallServiceMP, String productToAdd) {

        Product product = new Product(productToAdd);

        Call<Product> call = iApiCallServiceMP.createProduct(product);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
 //               Product productResponse = response.body();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // GET STRING PRIKORMLISTS
    public String[] GETPrikormLists(IApiCallServiceMP iApiCallServiceMP) {
        String[] result;
        int leng = 100;
        result = new String[leng];

        Call<List<PrikormList>> call = iApiCallServiceMP.getPrikormLists();

        call.enqueue(new Callback<List<PrikormList>>() {
            @Override
            public void onResponse(Call<List<PrikormList>> call, Response<List<PrikormList>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PrikormList> prikormLists = response.body();
                //int leng1 = products.size();
                int i = 0;
                for (PrikormList prikormList:prikormLists){
                    String content = "";
                    content += prikormList.getDateMeal()+"\n";
                    content += prikormList.getMeal()+"\n";
                    content += prikormList.getProduct()+"   ";
                    content += prikormList.getWeight()+"\n";
                    content += prikormList.getReaction();
                    result[i] = content;
                    i++;
                }
            }
            @Override
            public void onFailure(Call<List<PrikormList>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }
    // POST STRING PRIKORMLIST
    public void POSTPrikormLists(IApiCallServiceMP iApiCallServiceMP, int idUser,String dateMeal, String meal,String product,int weight,String reaction) {

        PrikormList prikormList = new PrikormList(idUser, dateMeal, meal, product, weight, reaction);

        Call<PrikormList> call = iApiCallServiceMP.createPrikormList(prikormList);

        call.enqueue(new Callback<PrikormList>() {
            @Override
            public void onResponse(Call<PrikormList> call, Response<PrikormList> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<PrikormList> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // GET STRING USERS
    public String[] GETUsers(IApiCallServiceMP iApiCallServiceMP) {
        String[] result;
        int leng = 100;
        result = new String[leng];

        Call<List<User>> call = iApiCallServiceMP.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<User> users = response.body();
                //int leng1 = products.size();
                int i = 0;
                for (User user:users){
                    String content = "";
                    content += user.getId();
                    content += user.getUsername();
                    content += user.getChildName();
                    content += user.getPassword();
                    result[i] = content;
                    i++;
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }
    // POST STRING USERS
    public void POSTUsers(IApiCallServiceMP iApiCallServiceMP, String username, String childName, String password,String email,String phoneno) {

        User user = new User(username,childName,password,email,phoneno);

        Call<User> call = iApiCallServiceMP.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
