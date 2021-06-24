package com.example.myfirstprikorm.ui.mymenu;

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

import com.example.myfirstprikorm.R;
import com.example.myfirstprikorm.Addmeal;
import com.example.myfirstprikorm.api.ApiRequestsMP;

public class MymenuFragment extends Fragment {

    private ListView listViewResult;
    Button addMealBtn;

    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_mymenu,container, false);

        String idUser_;

        Bundle extras = getActivity().getIntent().getExtras();

        if(extras!=null){
            idUser_ = extras.getString("id");
        }
        else{
            idUser_ = "1";
        }

        addMealBtn = root.findViewById(R.id.addMealBtn);
        listViewResult = root.findViewById(R.id.listView_menu);

        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();

        final String[] result = apiRequestsMP_.GETPrikormListsById(Integer.parseInt(idUser_));
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,result);
        listViewResult.setAdapter(arrayAdapter);

        addMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Addmeal.class);
                intent.putExtra("id",idUser_);
                startActivity(intent);
            }
        });

        return root;
    }

}