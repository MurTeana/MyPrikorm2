package com.example.myfirstprikorm.ui.tmymenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfirstprikorm.Addmeal;
import com.example.myfirstprikorm.R;
import com.example.myfirstprikorm.TAddmeal;
import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.example.myfirstprikorm.api.IApiCallServiceMP;

public class TMymenuFragment extends Fragment {

    private ListView listViewResult;
    Button addMealBtn;

    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_mymenu,container, false);

        addMealBtn = root.findViewById(R.id.addMealBtn);
        listViewResult = root.findViewById(R.id.listView_menu);

        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
        IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_._ApiCallServiceMP();

        final String[] result = apiRequestsMP_.GETPrikormLists(iApiCallServiceMPMyPrikorm);
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,result);
        listViewResult.setAdapter(arrayAdapter);

        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TAddmeal.class);
                startActivity(intent);
            }
        });

        return root;
    }

}