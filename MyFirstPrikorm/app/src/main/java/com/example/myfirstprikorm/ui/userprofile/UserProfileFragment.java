package com.example.myfirstprikorm.ui.userprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstprikorm.Addmeal;
import com.example.myfirstprikorm.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileFragment extends Fragment {

    TextView username;

    TextInputLayout childname, email, password, phoneno;
    Button changeData_btn;
    String _NAME, _CHILDNAME, _EMAIL, _PASSWORD, _PHONENO;

    DatabaseReference reference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_userprofile,container, false);

    reference = FirebaseDatabase.getInstance().getReference("users");

    //Hooks
        username = root.findViewById(R.id.name);
        childname = root.findViewById(R.id.childname);
        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        phoneno = root.findViewById(R.id.phoneno);
        changeData_btn=root.findViewById(R.id.changeData_btn);

        showAllUserData();

        changeData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(view);
            }
        });
        return root;
    };

    private void showAllUserData() {

        Intent intent = getActivity().getIntent();

        _NAME = intent.getStringExtra("name");
        _CHILDNAME = intent.getStringExtra("childname");
        _EMAIL = intent.getStringExtra("email");
        _PASSWORD = intent.getStringExtra("password");
        _PHONENO = intent.getStringExtra("phoneno");

        username.setText(_NAME);
        childname.getEditText().setText(_CHILDNAME);
        email.getEditText().setText(_EMAIL);
        password.getEditText().setText(_PASSWORD);
        phoneno.getEditText().setText(_PHONENO);

    }

  public void update(View view){
        if( isChildNameChanged() | isEmailChanged() | isPasswordChanged() | isPhoneNoChanged()) {

            Toast.makeText(getActivity(), "Данные обновлены", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(getActivity(), "Отсутствуют данные для обновления", Toast.LENGTH_LONG).show();

        }
    }

    private boolean isChildNameChanged() {
        if (!_CHILDNAME.equals(childname.getEditText().getText().toString())){
            reference.child(_NAME).child("childname").setValue(childname.getEditText().getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if (!_EMAIL.equals(email.getEditText().getText().toString())){
            reference.child(_NAME).child("email").setValue(email.getEditText().getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())){
            reference.child(_NAME).child("password").setValue(password.getEditText().getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isPhoneNoChanged() {
        if (!_PHONENO.equals(phoneno.getEditText().getText().toString())){
            reference.child(_NAME).child("phoneno").setValue(phoneno.getEditText().getText().toString());
            return true;
        } else {
            return false;
        }
    }

}