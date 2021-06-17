package com.example.myfirstprikorm;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.example.myfirstprikorm.api.IApiCallServiceMP;
import com.google.android.material.textfield.TextInputLayout;

public class TSignUp extends AppCompatActivity {

    TextInputLayout regName, regChildname, regEmail, regPhoneno, regPassword;
    Button regBtn, regToLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        regName = findViewById(R.id.regName);
        regChildname = findViewById(R.id.regChildname);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regPhoneno = findViewById(R.id.regPhoneno);
        regBtn = findViewById(R.id.regBtn);
        regToLoginBtn = findViewById(R.id.regToLoginBtn);
    }

    private  Boolean validateName(){
        String val=regName.getEditText().getText().toString();

        if(val.isEmpty()){
            regName.setError("Поле не может быть пустым");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private  Boolean validateChildname(){
        String val=regChildname.getEditText().getText().toString();

        if(val.isEmpty()){
            regChildname.setError("Поле не может быть пустым");
            return false;
        } else {
            regChildname.setError(null);
            regChildname.setErrorEnabled(false);
            return true;
        }
    }

    private  Boolean validateEmail(){
        String val=regEmail.getEditText().getText().toString();
        //String noWhiteSpace = "(?=\\s+$)"; "\\A\\w{4,20}\\z"
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            regEmail.setError("Поле не может быть пустым");
            return false;
        } else if(!val.matches(emailPattern)){
            regEmail.setError("Неверный адрес эл.почты");
            return false;
        } else {
            regEmail.setError(null);
            return true;
        }
    }

    private  Boolean validatePhoneno(){
        String val=regPhoneno.getEditText().getText().toString();

        if(val.isEmpty()){
            regPhoneno.setError("Поле не может быть пустым");
            return false;
        }
        else {
            regPhoneno.setError(null);
            return true;
        }
    }

    private  Boolean validatePassword(){
        String val=regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
             //"(?=.*[0-9])" +           //at least 1 digit
             //"(?=.*[a-z])" +          //at least 1 lower case letter
             //"(?=.*[A-Z])" +          //at least 1 upper case letter
             "(?=.*[a-zA-Z])" +          // any letter
             "(?=.*[@#$%^&+=])" +        // at least 1 special character
             "(?=\\S+$)" +               // no white spaces
             ".{4,}" +                   // at least 4 charaters
             "$";                        //

        if(val.isEmpty()){
            regPassword.setError("Поле не может быть пустым");
            return false;
        } else if(!val.matches(passwordVal)){
            regPassword.setError("Пароль слишком простой");
            return false;
        }else {
            regPassword.setError(null);
            return true;
        }
    }

    //Save data in database on button click
    public void registerUser(View view) {
        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
        if(!validateName() | !validateChildname() | !validateEmail() | !validatePhoneno() | !validatePassword()) {
            return;
        }
        //Get all values in Strings
        String name=regName.getEditText().getText().toString();
        String childname=regChildname.getEditText().getText().toString();
        String email=regEmail.getEditText().getText().toString();
        String phoneno=regPhoneno.getEditText().getText().toString();
        String password=regPassword.getEditText().getText().toString();

        IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_._ApiCallServiceMP();
        apiRequestsMP_.POSTUsers(iApiCallServiceMPMyPrikorm, name, childname, password, email, phoneno);

        Toast.makeText(this, "Вы успешно зарегистрированы!", Toast.LENGTH_LONG).show();
    }

    public void gotoLogin(View view) {
        finish();
    }

    }