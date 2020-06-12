package com.example.mydiet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydiet.Model.ActivityType;
import com.example.mydiet.Model.UserModel;
import com.example.mydiet.SQLHelper.UserHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.tinggiBadan)
    EditText tinggiBadan;
    @BindView(R.id.beratBadan)
    EditText beratBadan;
    @BindView(R.id.edUsia)
    EditText edUsia;
    @BindView(R.id.rdSex)
    RadioGroup rdSex;
    @BindView(R.id.spinnerActvity)
    Spinner spinnerActvity;
    @BindView(R.id.rdLaki)
    RadioButton rdLaki;
    @BindView(R.id.rdPerempuan)
    RadioButton rdPerempuan;

    private Double selectedActivity;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        userHelper = new UserHelper(this);
        setSpinner();
    }

    private void setSpinner() {
        List<ActivityType> activityTypeList = new ArrayList<>();
        activityTypeList.add(new ActivityType(1.2, "Sedenter (minim aktivitas fisik,jarang/tak pernah olahraga)"));
        activityTypeList.add(new ActivityType(1.375, "Jarang olahraga (1-3 hari/minggu):"));
        activityTypeList.add(new ActivityType(1.55, "Normal olahraga (3-5 hari/minggu)"));
        activityTypeList.add(new ActivityType(1.725, "Sering olahraga (6-7 hari/minggu)"));
        activityTypeList.add(new ActivityType(1.9, "Sangat sering olahraga (setiap hari bisa 2 kali dalam sehari)"));

        ArrayAdapter<ActivityType> adapter = new ArrayAdapter<ActivityType>(this,
                android.R.layout.simple_spinner_item, activityTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActvity.setAdapter(adapter);
        spinnerActvity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ActivityType activityType = (ActivityType) parent.getSelectedItem();
                selectedActivity = activityType.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        if(validatorForm()){
            String name = email.getText().toString();
            int tinggi = Integer.parseInt(tinggiBadan.getText().toString());
            int berat = Integer.parseInt(beratBadan.getText().toString());
            int usia =  Integer.parseInt(edUsia.getText().toString());
            Integer rgSex = rdSex.getCheckedRadioButtonId();
            int sex;
            if (rgSex == rdLaki.getId()) {
                sex = 1;
            } else {
                sex = 2;
            }
            Double kalories = getCalories(tinggi,berat,usia,sex,selectedActivity);
            Long id = userHelper.insertData(new UserModel(null,name,tinggi,usia,berat,sex,selectedActivity,kalories));
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }

    }

    private boolean validatorForm() {

        if(email.getText().toString().matches("")){
            email.setError("Required Field");
            return false;
        }

        if(tinggiBadan.getText().toString().matches("") ){
            tinggiBadan.setError("Required Field");
            return false;
        }

        if(beratBadan.getText().toString().matches("") ){
            beratBadan.setError("Required Field");
            return false;
        }

        if(edUsia.getText().toString().matches("") ){
            edUsia.setError("Required Field");
            return false;
        }

        return  true;
    }

    private Double getCalories(int tinggi, int berat, int usia, int sex,double typeActivity) {
        if(sex == 1){
            return ( 447.593  + (9.247 * berat) + (3.098 * tinggi)  - (4.33 * usia)) * typeActivity;
        }else {
            return (88.362 + (13.397  * berat) + (4.799 * tinggi)  - (5.677 * usia)) * typeActivity;
        }

    }
}
