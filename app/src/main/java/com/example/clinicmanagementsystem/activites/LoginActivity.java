package com.example.clinicmanagementsystem.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.db.AccountDataBase;
import com.google.android.material.textfield.TextInputEditText;

//Login Activity
public class LoginActivity extends AppCompatActivity {

    TextView tv_singup,tv_error, tv_forget;
    TextInputEditText insurance, password;
    Button btn_login;
    CheckBox cb_remind;
    int saveLogin;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_singup = findViewById(R.id.login_tv_signup);
        btn_login = findViewById(R.id.login_btn_login);
        insurance = findViewById(R.id.login_et_insurance);
        password = findViewById(R.id.login_et_password);
        tv_error = findViewById(R.id.login_tv_error);
        cb_remind = findViewById(R.id.login_ch_remember);

        String Patient = insurance.getText().toString();
        String Password = password.getText().toString();
        tv_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // implicit intent to go on Signup Activity
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SignUp");
                startActivity(intent);
            }
        });
        AccountDataBase accountDataBase = new AccountDataBase(getBaseContext());
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if patient and password is exist in account_patient table , you can login to main activity
                boolean isExist = accountDataBase.CheckPatientExist(Patient, Password);
                if (isExist) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("Patient", Patient);
                    startActivity(intent);
                } else {
                    tv_error.setText(R.string.login_tv_error);
                }
            }
        });
        cb_remind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb_remind.isChecked()) {
                    loginPrefsEditor.putInt("saveLogin", 1);
                    loginPrefsEditor.putString("insurance", Patient);
                    loginPrefsEditor.putString("password", Password);
                    loginPrefsEditor.apply();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
            }
        });
        save();
    }

    public void save() {
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getInt("saveLogin", 0);
        if (saveLogin == 1) {
            insurance.setText(loginPreferences.getString("insurance", ""));
            password.setText(loginPreferences.getString("password", ""));
            cb_remind.setChecked(true);
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}