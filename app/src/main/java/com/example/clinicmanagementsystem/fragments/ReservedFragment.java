package com.example.clinicmanagementsystem.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinicmanagementsystem.Adapters.AdapterDoctor;
import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.db.AccountDataBase;
import com.example.clinicmanagementsystem.models.Doctor;

import java.util.ArrayList;


public class ReservedFragment extends Fragment {

    String name_dr="";
    String appointment ="";
    public ReservedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    AccountDataBase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentdb = new AccountDataBase(getActivity());
        db= new AccountDataBase(getActivity());
        View view = inflater.inflate(R.layout.fragment_reserved,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_reserved);
        ArrayList<Doctor> reserved = new ArrayList<>();

        ArrayList<Doctor> list = db.getAllReserved();
        for(Doctor doctor :list){
            name_dr=doctor.getName_dr();
            appointment=doctor.getAppointment();
            if(list!=null)
                reserved.add(new Doctor(name_dr, appointment));
        }
        AdapterDoctor adapterDoctor =new AdapterDoctor(reserved,getActivity());
        recyclerView.setAdapter(adapterDoctor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}