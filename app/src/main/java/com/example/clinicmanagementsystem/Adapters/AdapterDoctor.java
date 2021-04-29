package com.example.clinicmanagementsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.db.AccountDataBase;
import com.example.clinicmanagementsystem.interfaces.OnItemClickListener;
import com.example.clinicmanagementsystem.models.Doctor;

import java.util.ArrayList;

public class AdapterDoctor extends RecyclerView.Adapter<AdapterDoctor.DoctorViewHolder> {
    ArrayList<Doctor> doctors;
    OnItemClickListener onItemClickListener;
    Context context;
    public AdapterDoctor(ArrayList<Doctor>doctors,OnItemClickListener onItemClickListener){
        this.doctors=doctors;
       this.onItemClickListener=onItemClickListener;
    }
    public AdapterDoctor(ArrayList<Doctor>doctors, Context context){
        this.doctors=doctors;
        this.context=context;
    }
    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_custom_item,parent,false);
        return new DoctorViewHolder(v);
    }
    AccountDataBase dataBase;
    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.bind(doctor);
        AccountDataBase db = new AccountDataBase(context);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                db.delete(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
    class DoctorViewHolder extends  RecyclerView.ViewHolder{
        TextView tv_name , tv_appointment;
        Doctor doctor;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.item_tv_namedr);
            tv_appointment=itemView.findViewById(R.id.item_tv_appointment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, ReservedActivity.class);
//                    context.startActivity(intent);
                    onItemClickListener.onItemClick(doctor);
                }
            });
        }

        public void bind(Doctor doctor) {
            this.doctor=doctor;
            tv_name.setText(doctor.getName_dr());
            tv_appointment.setText(doctor.getAppointment());
        }
    }

}

