package com.example.clinicmanagementsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicmanagementsystem.R;
import com.example.clinicmanagementsystem.db.AccountDataBase;
import com.example.clinicmanagementsystem.interfaces.OnItemClickListener;
import com.example.clinicmanagementsystem.models.Doctor;

import java.util.ArrayList;

public class AdapterReserved extends RecyclerView.Adapter<AdapterReserved.ReservedViewHolder> {
    ArrayList<Doctor> doctors;
    Context context;
    public AdapterReserved(ArrayList<Doctor> doctors, Context context){
        this.doctors=doctors;
        this.context=context;
    }
    @NonNull
    @Override
    public ReservedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_custom_item,parent,false);
        return new ReservedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservedViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.bind(doctor);
        AccountDataBase db = new AccountDataBase(context);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                doctors.remove(position);
                notifyDataSetChanged();
                db.delete(position);
                Toast.makeText(context,"تم الغاء حجز الموعد في العيادة.",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    class ReservedViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name , tv_appointment;
        Doctor doctor;
        public ReservedViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.item_tv_namedr);
            tv_appointment=itemView.findViewById(R.id.item_tv_appointment);
        }
        public void bind(Doctor doctor) {
            this.doctor=doctor;
            tv_name.setText(doctor.getName_dr());
            tv_appointment.setText(doctor.getAppointment());
        }
    }
}
