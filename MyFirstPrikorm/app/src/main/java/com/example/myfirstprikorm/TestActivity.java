package com.example.myfirstprikorm;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstprikorm.api.IApiCallServiceMP;
import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.google.android.material.textfield.TextInputLayout;

public class TestActivity extends AppCompatActivity {

    TextInputLayout regName, regChildname, regEmail, regPhoneno, regPassword;
    Button regBtn, regToLoginBtn;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

//        listViewResult = findViewById(R.id.listView_prod);

        //Hooks
        regName = findViewById(R.id.regName);
        regChildname = findViewById(R.id.regChildname);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regPhoneno = findViewById(R.id.regPhoneno);
        regBtn = findViewById(R.id.regBtn);
        regToLoginBtn = findViewById(R.id.regToLoginBtn);

        //Get all values in Strings
//        String name=regName.getEditText().getText().toString();
//        String childname=regChildname.getEditText().getText().toString();
//        String email=regEmail.getEditText().getText().toString();
//        String phoneno=regPhoneno.getEditText().getText().toString();
//        String password=regPassword.getEditText().getText().toString();

        ArrayAdapter<String> arrayAdapter;

        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view, apiRequestsMP_);
            }
        });
//        textViewResult = apiRequestsMP_.AddProductList(iApiCallServiceMPMyPrikorm, textViewResult);

//        final String[] result = apiRequestsMP_.GETUsers(iApiCallServiceMPMyPrikorm);
//        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,result);
//        listViewResult.setAdapter(arrayAdapter);
    }
    public void registerUser(View view,ApiRequestsMP apiRequestsMP_) {
//        if(!validateName() | !validateChildname() | !validateEmail() | !validatePhoneno() | !validatePassword()) {
//            return;
//        }

        //Get all values in Strings
        String name=regName.getEditText().getText().toString();
        String childname=regChildname.getEditText().getText().toString();
        String email=regEmail.getEditText().getText().toString();
        String phoneno=regPhoneno.getEditText().getText().toString();
        String password=regPassword.getEditText().getText().toString();

        IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_._ApiCallServiceMP();

        apiRequestsMP_.POSTUsers(iApiCallServiceMPMyPrikorm, name, childname, password,email,phoneno);

        Toast.makeText(this, "Вы успешно зарегистрированы!", Toast.LENGTH_LONG).show();
    }
}

