package com.example.myfirstprikorm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.example.myfirstprikorm.api.login.LoginRequest;
import com.example.myfirstprikorm.api.login.LoginResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button callSignUp, login_btn;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.signup_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        //Hooks
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                //Intent intent = new Intent(Login.this, Dashboard.class);
                //startActivity(intent);
            }
        });
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getEditText().getText().toString().trim());
        loginRequest.setPassword(password.getEditText().getText().toString().trim());

        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
        Call<LoginResponse> loginResponseCall = apiRequestsMP_.getUserService().userLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = new Intent(Login.this, Dashboard.class);

                            String userIdFromDB = loginResponse.getId();
                            String nameFromDB = loginResponse.getUsername();
                            String childnameFromDB = loginResponse.getChildName();
                            String emailFromDB = loginResponse.getEmail();
                            String phonenoFromDB = loginResponse.getPhoneno();
                            String passwordFromDB = loginResponse.getPassword();

                            intent.putExtra("id",userIdFromDB);
                            intent.putExtra("name",nameFromDB);
                            intent.putExtra("childname",childnameFromDB);
                            intent.putExtra("email",emailFromDB);
                            intent.putExtra("phoneno",phonenoFromDB);
                            intent.putExtra("password",passwordFromDB);

                            startActivity(intent);
                        }
                    },700);
                }else{
                    Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this,"Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginUser() {
        if(!validateUsername() | !validatePassword()) {
            return;
        } else {
            login();
        }
    }

    private void isUser(){
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
    }

    private  Boolean validateUsername(){
        String val=username.getEditText().getText().toString();

        if(val.isEmpty()){
            username.setError("Поле не может быть пустым");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private  Boolean validatePassword(){
        String val=password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Поле не может быть пустым");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    //    private void isUser() {
//        final String userEnteredUsername = username.getEditText().getText().toString().trim();
//        final String userEnteredPassword = password.getEditText().getText().toString().trim();
//
//        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        //Query checkUsername = reference.orderByChild("name").equalTo(userEnteredUsername); //????
//
////        checkUsername.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
// //               if(snapshot.exists()) {
//
//                    username.setError(null);
//                    username.setErrorEnabled(false);
//
////                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
//
////                    if(passwordFromDB.equals(userEnteredPassword)) {
//
//                        username.setError(null);
//                        username.setErrorEnabled(false);
//
////                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
////                        String childnameFromDB = snapshot.child(userEnteredUsername).child("childname").getValue(String.class);
////                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
////                        String phonenoFromDB = snapshot.child(userEnteredUsername).child("phoneno").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
//
////                        intent.putExtra("name", nameFromDB);
////                        intent.putExtra("childname", childnameFromDB);
////                        intent.putExtra("email", emailFromDB);
////                        intent.putExtra("phoneno", phonenoFromDB);
////                        intent.putExtra("password", passwordFromDB);
//
//                        startActivity(intent);
////                    }
////                    else {
////                        password.setError("Неверный пароль");
////                        password.requestFocus();
////                    }
////                }
////                else {
////                    username.setError("Пользователь с таким именем не зарегистрирован");
////                    username.requestFocus();
////                }
////            }
//
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//    }
}