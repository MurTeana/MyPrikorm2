package com.example.myfirstprikorm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regChildname, regEmail, regPhoneno, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

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

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
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

    //Save data in Firebase on button click
    public void registerUser(View view) {
        if(!validateName() | !validateChildname() | !validateEmail() | !validatePhoneno() | !validatePassword()) {
            return;
        }

        //Get all values in Strings
        String name=regName.getEditText().getText().toString();
        String childname=regChildname.getEditText().getText().toString();
        String email=regEmail.getEditText().getText().toString();
        String phoneno=regPhoneno.getEditText().getText().toString();
        String password=regPassword.getEditText().getText().toString();
        UserHelperClass helperClass = new UserHelperClass(name, childname, email, phoneno, password);
        reference.child(name).setValue(helperClass);

        Toast.makeText(this, "Вы успешно зарегистрированы!", Toast.LENGTH_LONG).show();
    }

    public void gotoLogin(View view) {
        finish();
    }

    }