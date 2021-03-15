package com.example.myfirstprikorm.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfirstprikorm.Dashboard;
import com.example.myfirstprikorm.Login;
import com.example.myfirstprikorm.R;
import com.example.myfirstprikorm.SignUp;
import com.example.myfirstprikorm.UserHelperClass;
import com.example.myfirstprikorm.UserHelperClassProd;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    private ListView listView;
    TextInputLayout addProd;
    Button addProdBtn;

    DatabaseReference databaseReference;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_products,container, false);

    addProd = root.findViewById(R.id.addProd);
    addProdBtn = root.findViewById(R.id.addProdBtn);

    databaseReference=FirebaseDatabase.getInstance().getReference("products");

    listView=(ListView) root.findViewById(R.id.listView_prod);
    arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arrayList);
    listView.setAdapter(arrayAdapter);

        addProdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProdToDB(view);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(UserHelperClassProd.class).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    return root;
    }
    private  Boolean validateProduct(){

        String val=addProd.getEditText().getText().toString();

        if(val.isEmpty()){
            addProd.setError("Поле не может быть пустым");
            return false;
        } else {
            addProd.setError(null);
            addProd.setErrorEnabled(false);
            return true;
        }
    }

    public void addProdToDB(View view) {
        if(!validateProduct()) {
            return;
        }
        //Get all values in Strings
        String product=addProd.getEditText().getText().toString();
        UserHelperClassProd helperClass = new UserHelperClassProd(product);
        databaseReference.child(product).setValue(helperClass);
    }
}