package com.example.myfirstprikorm.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstprikorm.R;
import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.google.android.material.textfield.TextInputLayout;

public class ProductsFragment extends Fragment {

    TextInputLayout addProd;
    Button addProdBtn;

    private ListView listViewResult;

    ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_products,container, false);

    addProd = root.findViewById(R.id.addProd);
    addProdBtn = root.findViewById(R.id.addProdBtn);
    listViewResult = root.findViewById(R.id.listView_prod);

    final String[] result = apiRequestsMP_.GETProducts();
    arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,result);
    listViewResult.setAdapter(arrayAdapter);

    addProdBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addProdToDB(view);
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
        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
        if(!validateProduct()) {
            return;
        }
        else{
            String productToAdd=addProd.getEditText().getText().toString();
            apiRequestsMP_.POSTProduct(productToAdd);
        }
    }

}