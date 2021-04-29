package com.example.clinicmanagementsystem.models;

public class Doctor {
    String Name_dr;
    String Appointment;

    public Doctor(String name_dr, String appointment) {
        Name_dr = name_dr;
        Appointment = appointment;
    }

    public String getName_dr() {
        return Name_dr;
    }

    public void setName_dr(String name_dr) {
        Name_dr = name_dr;
    }

    public String getAppointment() {
        return Appointment;
    }

    public void setAppointment(String appointment) {
        Appointment = appointment;
    }
}
