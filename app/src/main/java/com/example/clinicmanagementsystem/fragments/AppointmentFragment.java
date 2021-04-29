package com.example.clinicmanagementsystem.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clinicmanagementsystem.Adapters.AdapterDoctor;
import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.interfaces.OnItemClickListener;
import com.example.clinicmanagementsystem.models.Doctor;

import java.util.ArrayList;

public class AppointmentFragment extends Fragment {

    private onFragmentClickListener listener;
    public AppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentClickListener)
            listener= (onFragmentClickListener) context;
        else
            throw new ClassCastException("Your Activity does not implement OnFragmentClickListener");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_appointment);
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));
        doctors.add(new Doctor(getString(R.string.name_dr_1),getString(R.string.appoinment_dr_1)));

        AdapterDoctor adapterdoctors = new AdapterDoctor(getActivity(),doctors, new OnItemClickListener() {
            @Override
            public void onItemClick(Doctor doctor) {
                listener.onFragmentInteraction(doctor);
            }
        });
        recyclerView.setAdapter(adapterdoctors);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    public interface onFragmentClickListener {
        void  onFragmentInteraction(Doctor doctor);
    }
}