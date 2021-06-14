package com.example.myfirstprikorm;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstprikorm.api.IApiCallServiceMP;
import com.example.myfirstprikorm.api.ApiRequestsMP;

public class TestActivity extends AppCompatActivity {

    private TextView textViewResult;
    private ListView listViewResult;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test);

        textViewResult = findViewById(R.id.text_view_result);
        listViewResult = findViewById(R.id.listView_prod);

        ArrayAdapter<String> arrayAdapter;

//        JsonPlaceHolderApiRequests jsonPlaceHolderApiRequests = new JsonPlaceHolderApiRequests();
//        JsonPlaceHolderApiCallService jsonPlaceHolderApiCallService = jsonPlaceHolderApiRequests.jsonPlaceHolderApiCallService_();
//        textViewResult = jsonPlaceHolderApiRequests.AddList(jsonPlaceHolderApiCallService, textViewResult);
        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
        IApiCallServiceMP iApiCallServiceMPMyPrikorm = apiRequestsMP_.jsonPlaceHolderApiCallServiceMyPrikorm();
        textViewResult = apiRequestsMP_.AddProductList(iApiCallServiceMPMyPrikorm, textViewResult);

        final String[] result = apiRequestsMP_.AddProductListString(iApiCallServiceMPMyPrikorm);
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,result);
        listViewResult.setAdapter(arrayAdapter);
    }
}
