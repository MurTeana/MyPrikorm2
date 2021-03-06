package com.example.myfirstprikorm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstprikorm.api.ApiRequestsMP;
import com.example.myfirstprikorm.api.IApiCallServiceMP;
import com.example.myfirstprikorm.ui.mymenu.MymenuFragment;
import com.example.myfirstprikorm.ui.mymenu.MymenuViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Retrofit;

import static java.lang.Integer.parseInt;

public class Addmeal extends AppCompatActivity {

    public String idUser_;
    TextInputLayout addValue;
    Button addMenuBtn;
    Spinner addMealfrSp,addProdfrSp;
    EditText addData;

    ArrayList<String> arrayProductsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeal);

        addData = findViewById(R.id.date_time_input);
        addValue = findViewById(R.id.addValue);
        addMenuBtn = findViewById(R.id.addMenuBtn);

        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            idUser_ = extras.getString("id");
        }
        else{
            idUser_ = "1";
        }
 //       addIdUser.setText(idUser_);
        addData.setInputType(InputType.TYPE_NULL);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(addData);
            }
        });
// Meal
        addMealfrSp = findViewById(R.id.mealspinner);

        String[] meal_res = apiRequestsMP_.GETMeals();
        ArrayAdapter<String> adapterMeal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,meal_res);
        adapterMeal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addMealfrSp.setAdapter(adapterMeal);
        addMealfrSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
// Product
        addProdfrSp = findViewById(R.id.prodspinner);


        String[] products_res = apiRequestsMP_.GETProducts();

        ArrayAdapter<String> adapterProducts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,products_res);
        adapterProducts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addProdfrSp.setAdapter(adapterProducts);
        addProdfrSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMenuToDB(view);
            }
        });

    }
// Date Time
    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY,i);
                        calendar.set(Calendar.MINUTE,i1);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(Addmeal.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };
        new DatePickerDialog(Addmeal.this, dateSetListener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
// Validate
    private  Boolean validateData(){
        String val=addData.getText().toString();

        if(val.isEmpty()){
            addData.setError("???????? ???? ?????????? ???????? ????????????");
            return false;
        } else {
            addData.setError(null);
            return true;
        }
    }

    private  Boolean validateValue(){
        String val=addValue.getEditText().getText().toString();

        if(val.isEmpty()){
            addValue.setError("???????? ???? ?????????? ???????? ????????????");
            return false;
        } else {
            addValue.setError(null);
            addValue.setErrorEnabled(false);
            return true;
        }
    }
// AddMenu
    public void addMenuToDB(View view) {


        if(!validateData() | !validateValue() ) {
            return;
        }

        //Get all values in Strings
        int idUser = Integer.parseInt(idUser_);
        String dateMeal = addData.getText().toString();
        String meal = addMealfrSp.getSelectedItem().toString();
        String product = addProdfrSp.getSelectedItem().toString();
        int weight = parseInt(addValue.getEditText().getText().toString());
        String reaction = "-";

        ArrayAdapter<String> arrayAdapter;

        ApiRequestsMP apiRequestsMP_ = new ApiRequestsMP();
        apiRequestsMP_.POSTPrikormLists(idUser, dateMeal, meal, product, weight, reaction);

        Intent intent = new Intent(Addmeal.this, MymenuViewModel.class);
        startActivity(intent);
    }
}