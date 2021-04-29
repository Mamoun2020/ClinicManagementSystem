package com.example.clinicmanagementsystem.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.db.AccountDataBase;
import com.example.clinicmanagementsystem.models.AccountPatient;
import com.google.android.material.textfield.TextInputEditText;

//Signup Activity
public class SignupActivity extends AppCompatActivity {
    TextView tv_login,tv_result;
    TextInputEditText insurance,phone,password,address;
    Button btn_Sign_up;
    AccountDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        tv_login=findViewById(R.id.signup_tv_Login);
        insurance=findViewById(R.id.signup_et_insurance);
        phone=findViewById(R.id.singup_et_phone);
        password=findViewById(R.id.signup_et_password);
        address=findViewById(R.id.signup_et_city);
        btn_Sign_up=findViewById(R.id.Signup_btn_Signup);
        tv_result=findViewById(R.id.signup_tv_result);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // implicit intent to go on Login activity if u have account already or u sign up have success
                Intent intent = new Intent()
                        .setAction("android.intent.action.LogIn");
                startActivity(intent);
            }
        });
        db = new AccountDataBase(this);
        btn_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Insurance= String.valueOf(insurance.getText());
                String Phone= String.valueOf(phone.getText());
                String Password= String.valueOf(password.getText());
                String Address= String.valueOf(address.getText());
                // take data from field to create account in DB
                if (!Insurance.equals(getString(R.string.signup_et_username)) && !Password.equals(getString(R.string.signup_et_password))) {
                    //add these data to insurance object to send it into DB , to account table
                    AccountPatient insurance = new AccountPatient(Insurance, Phone, Password, Address);
                    if (insurance != null) {
                        // insert account object
                        boolean res = db.insertPatient(insurance);
                        if (res) {
                            // if res return true ,that is means success add new account
                            tv_result.setText(R.string.signup_tv_result1);
                        } else {
                            tv_result.setText(R.string.signup_tv_result3);
                        }
                    }
                }else {
                    tv_result.setText(R.string.signup_tv_result2);
                }
            }
        });
    }
}