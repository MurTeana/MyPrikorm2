package com.example.myfirstprikorm.api;

import com.example.myfirstprikorm.model.Meal;
import com.example.myfirstprikorm.model.PrikormList;
import com.example.myfirstprikorm.model.Product;
import com.example.myfirstprikorm.model.User;
import com.example.myfirstprikorm.api.login.UserService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Response;

public class ApiRequestsMP {

    private Context context;

    public Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.2:45455")
                .client(okHttpClient)
                .build();

        return retrofit;
    }
    // Service
    public IApiCallServiceMP getCallService(){
        IApiCallServiceMP callService = getRetrofit().create(IApiCallServiceMP.class);
        return callService;
    }

    // GET PRODUCTS
    public String[] GETProducts() {
        String[] result;
        int leng = 100;
        result = new String[leng];

        Call<List<Product>> call = getCallService().getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    List<Product> products = response.body();

                    int i = 0;
                    for (Product product:products){
                        String content = "";
                        content = product.getProduct();
                        result[i] = content;
                        i++;
                     }
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }
    // POST PRODUCTS
    public void POSTProduct(String productToAdd) {

        Product product = new Product(productToAdd);

        Call<Product> call = getCallService().createProduct(product);

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

    // GET PRIKORMLISTS
    public String[] GETPrikormLists() {
        String[] result;
        int leng = 100;
        result = new String[leng];

        Call<List<PrikormList>> call = getCallService().getPrikormLists();

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
    // GET PRIKORMLISTS BY ID
    public String[] GETPrikormListsById(int userId) {
        String[] result;
        int leng = 100;
        result = new String[leng];

        Call<List<PrikormList>> call = getCallService().getPrikormListsByUserId(userId);

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
    // POST PRIKORMLIST
    public void POSTPrikormLists(int idUser,String dateMeal, String meal,String product,int weight,String reaction) {

        PrikormList prikormList = new PrikormList(idUser, dateMeal, meal, product, weight, reaction);

        Call<PrikormList> call = getCallService().createPrikormList(prikormList);

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

    // GET USERS
    public String[] GETUsers() {
        String[] result;
        int leng = 100;
        result = new String[leng];

        Call<List<User>> call = getCallService().getUsers();

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
    // POST USERS
    public void POSTUsers(String username, String childName, String password,String email,String phoneno) {

        User user = new User(username,childName,password,email,phoneno);

        Call<User> call = getCallService().createUser(user);

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
    // POST USERS
    public void PUTUsers(int id, String username, String childName, String password,String email,String phoneno) {

        User user = new User(username,childName,password,email,phoneno);

        Call<User> call = getCallService().updateUser(id, user);

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

    // Login
    public UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

    // GET MEALS
    public String[] GETMeals() {
        String[] result;
        int leng = 4;
        result = new String[leng];

        Call<List<Meal>> call = getCallService().getMeals();

        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Что-то пошло не так! Код: " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Meal> meals = response.body();
                int i = 0;
                for (Meal meal:meals){
                    String content = "";
                    content += meal.getMeal_();
                    result[i] = content;
                    i++;
                }
            }
            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }

}
