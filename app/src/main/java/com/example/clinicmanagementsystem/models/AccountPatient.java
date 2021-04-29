package com.example.clinicmanagementsystem.models;

public class AccountPatient {
    private String Insurance;
    private  String Phone;
    private String Password;
    private String Address;

    public AccountPatient(String insurance, String phone, String password, String address) {
        Insurance = insurance;
        Phone = phone;
        Password = password;
        Address = address;
    }

    public String getInsurance() {
        return Insurance;
    }

    public void setInsurance(String insurance) {
        Insurance = insurance;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
