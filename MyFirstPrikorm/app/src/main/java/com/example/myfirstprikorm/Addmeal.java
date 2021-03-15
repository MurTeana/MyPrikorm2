package com.example.myfirstprikorm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.myfirstprikorm.ui.mymenu.MymenuFragment;
import com.example.myfirstprikorm.ui.mymenu.MymenuViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class Addmeal extends AppCompatActivity {

    TextInputLayout addValue;
    Button addMenuBtn;
    Spinner addMealfrSp,addProdfrSp;
    EditText addData;
    String[] data = {"Завтрак", "Обед","Полдник","Ужин"};

    DatabaseReference databaseReference,databaseReference2;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeal);

        addData = findViewById(R.id.date_time_input);
        addValue = findViewById(R.id.addValue);
        addMenuBtn = findViewById(R.id.addMenuBtn);

        addData.setInputType(InputType.TYPE_NULL);

        databaseReference = FirebaseDatabase.getInstance().getReference("mymenu");

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTameDialog(addData);
            }
        });

        addMealfrSp = findViewById(R.id.mealspinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addMealfrSp.setAdapter(adapter1);
        addMealfrSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        databaseReference2=FirebaseDatabase.getInstance().getReference("products");
        addProdfrSp = findViewById(R.id.prodspinner);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrayList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addProdfrSp.setAdapter(adapter2);

        databaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(UserHelperClassProd.class).toString();
                arrayList.add(value);
                adapter2.notifyDataSetChanged();
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

        addProdfrSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showDateTameDialog(final EditText date_time_in) {
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

        private  Boolean validateData(){
            String val=addData.getText().toString();

            if(val.isEmpty()){
                addData.setError("Поле не может быть пустым");
                return false;
            } else {
                addData.setError(null);
                return true;
            }
        }

        private  Boolean validateValue(){
            String val=addValue.getEditText().getText().toString();

            if(val.isEmpty()){
                addValue.setError("Поле не может быть пустым");
                return false;
            } else {
                addValue.setError(null);
                addValue.setErrorEnabled(false);
                return true;
            }
        }

        public void addMenuToDB(View view) {
            if(!validateData() | !validateValue() ) {
                return;
            }
            //Get all values in Strings
            String data=addData.getText().toString();
            String meal=addMealfrSp.getSelectedItem().toString();
            String product=addProdfrSp.getSelectedItem().toString();
            String value=addValue.getEditText().getText().toString();

            UserHelperClassMenu helperClass = new UserHelperClassMenu(data,meal,product,value);
            databaseReference.child(data).setValue(helperClass);

            Intent intent = new Intent(Addmeal.this, MymenuViewModel.class);
            startActivity(intent);
        }
}