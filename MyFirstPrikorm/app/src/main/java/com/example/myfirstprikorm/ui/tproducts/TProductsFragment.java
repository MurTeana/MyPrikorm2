package com.example.myfirstprikorm.ui.tproducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstprikorm.R;
import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.example.myfirstprikorm.api.IApiCallServiceMP;
import com.google.android.material.textfield.TextInputLayout;

public class TProductsFragment extends Fragment {

    TextInputLayout addProd;
    Button addProdBtn;
    private ListView listViewResult;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_test,container, false);

    addProd = root.findViewById(R.id.addProd);
    addProdBtn = root.findViewById(R.id.addProdBtn);
    listViewResult = root.findViewById(R.id.listView_prod);

    ArrayAdapter<String> arrayAdapter;

    ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
    IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_._ApiCallServiceMP();

    final String[] result = apiRequestsMP_.GETProducts(iApiCallServiceMPMyPrikorm);
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
        //Get all values in Strings
        String productToAdd=addProd.getEditText().getText().toString();
        IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_._ApiCallServiceMP();
        apiRequestsMP_.POSTProduct(iApiCallServiceMPMyPrikorm, productToAdd);
    }
}