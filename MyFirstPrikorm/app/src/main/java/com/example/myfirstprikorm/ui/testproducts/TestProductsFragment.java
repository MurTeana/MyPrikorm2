package com.example.myfirstprikorm.ui.testproducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstprikorm.R;
import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.example.myfirstprikorm.api.IApiCallServiceMP;

public class TestProductsFragment extends Fragment {

    private TextView textViewResult;
    private ListView listViewResult;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_test,container, false);

    textViewResult = root.findViewById(R.id.text_view_result);
    listViewResult = root.findViewById(R.id.listView_prod);

    ArrayAdapter<String> arrayAdapter;

    ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
    IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_.jsonPlaceHolderApiCallServiceMyPrikorm();
    //textViewResult = apiRequestsMP_.AddProductList(iApiCallServiceMPMyPrikorm, textViewResult);

    final String[] result = apiRequestsMP_.AddProductListString(iApiCallServiceMPMyPrikorm);
    arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,result);
    listViewResult.setAdapter(arrayAdapter);

    return root;
    }


}