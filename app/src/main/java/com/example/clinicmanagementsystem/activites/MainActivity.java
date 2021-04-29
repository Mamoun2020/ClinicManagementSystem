package com.example.clinicmanagementsystem.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.db.AccountDataBase;
import com.example.clinicmanagementsystem.fragments.AppointmentFragment;
import com.example.clinicmanagementsystem.fragments.ReservedFragment;
import com.example.clinicmanagementsystem.models.Doctor;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import static java.lang.System.exit;
import static java.lang.System.in;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,AppointmentFragment.onFragmentClickListener {
    private BottomNavigationView navigation;
    MaterialToolbar toolbar;
    int saveLogin;
    TextView textView;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.bottomNavigationView);
        toolbar=findViewById(R.id.toolbar);
        toolbar = findViewById(R.id.toolbar);
        textView= findViewById(R.id.tv_bar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        loadFragment(new AppointmentFragment());
    }
    @Override
    protected void onStart() {
        super.onStart();
        navigation.setOnNavigationItemSelectedListener(MainActivity.this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;


        Bundle args = new Bundle();

        switch (item.getItemId()) {
            case R.id.nav_calendar:
                fragment = new AppointmentFragment();
                textView.setText(R.string.bottom_title_appointment);
                break;
            case R.id.nav_reserved:
                fragment = new ReservedFragment();
                textView.setText(R.string.bottom_title_reserved);
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_info:
//                Toast.makeText(getApplicationContext(),"Number Insurance : "+patient,Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                break;
//            case R.id.menu_reserved:
//                Intent intent = new Intent(getBaseContext(),ReservedActivity.class);
//                startActivity(intent);
//                break;
            case R.id.menu_exist:
                exist_dialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
       exist_dialog();
    }
    public void exist_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.main_title_dialog)
                .setMessage(R.string.main_message_dialog)
                .setPositiveButton(R.string.main_yes_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                        loginPrefsEditor = loginPreferences.edit();
                        saveLogin = loginPreferences.getInt("saveLogin", 0);
                        if (saveLogin == 1) {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.apply();
                        }
                        startActivity(a);
                    }
                })
                .setNegativeButton(R.string.main_no_dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog AD = builder.create();
        AD.show();
    }

    @Override
    public void onFragmentInteraction(Doctor doctor) {
        AccountDataBase db = new AccountDataBase(getBaseContext());
        Doctor doctor1 = new Doctor(doctor.getName_dr(),doctor.getAppointment());
        Log.d("name",doctor1.getName_dr());
        boolean res=db.insert_reserved(doctor1);
    }
}