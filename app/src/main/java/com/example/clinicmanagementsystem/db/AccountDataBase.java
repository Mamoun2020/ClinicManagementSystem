package com.example.clinicmanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.clinicmanagementsystem.models.AccountPatient;
import com.example.clinicmanagementsystem.models.Doctor;

import java.util.ArrayList;


public class AccountDataBase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 30;
    public static final String DB_NAME="patients";

    //this section of variables to patient Accounts
    public static final String TABLE_NAME="account_patients";
    public static final String PATIENT_CLN_INSURANCE="NumberInsurance";
    public static final String PATIENT_CLN_PHONE="NumberPhone";
    public static final String PATIENT_CLN_PASSWORD="Password";
    public static final String PATIENT_CLN_ADDRESS="Address";

    public static final  String TABLE_NAME2="patient_reserved";
    public static final String RESERVED_CLN_DOCTOR="NameDoctor";
    public static final String RESERVED_CLN_APPOINTMENT="Appointment";
    public static final String RESERVED_CLN_ID="ID";
//    public static final  String TABLE_NAME2="patient_reserved";
//    public static final String RESERVED_CLN_INDEX="index_";
//    public static final String RESERVED_CLN_DOCTOR="NameDoctor";
//    public static final String RESERVED_CLN_APPOINTMENT="Appointment";
//    public static final String RESERVED_CLN_INSURANCE="NumberInsurance";

    public AccountDataBase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+PATIENT_CLN_INSURANCE+" TEXT PRIMARY KEY NOT NULL,"+PATIENT_CLN_PHONE+" TEXT,"+PATIENT_CLN_PASSWORD+" TEXT NOT NULL,"+PATIENT_CLN_ADDRESS+" TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_NAME2+" ("+RESERVED_CLN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+RESERVED_CLN_DOCTOR+" TEXT NOT NULL,"+RESERVED_CLN_APPOINTMENT+" TEXT NOT NULL)");
//        db.execSQL("CREATE TABLE "+TABLE_NAME2+" ("+RESERVED_CLN_INDEX+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ RESERVED_CLN_DOCTOR+" TEXT,"+RESERVED_CLN_APPOINTMENT+" TEXT,"+RESERVED_CLN_INSURANCE+" TEXT NOT NULL ,FOREIGN KEY ("+RESERVED_CLN_INSURANCE+") REFERENCES "+TABLE_NAME+" ("+PATIENT_CLN_INSURANCE+"));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);
    }
    // insert patient from object AccountPatient class in the same package to DB in patient
    public boolean insertPatient(AccountPatient patient){
        SQLiteDatabase DB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PATIENT_CLN_INSURANCE,patient.getInsurance());
        values.put(PATIENT_CLN_PHONE,patient.getPhone());
        values.put(PATIENT_CLN_PASSWORD,patient.getPassword());
        values.put(PATIENT_CLN_ADDRESS,patient.getAddress());
        long result= DB.insert(TABLE_NAME,null,values);

        return result !=-1;
    }

    // check if Patient exist in account_patient table to login
    public boolean CheckPatientExist(String insurance, String password){
        SQLiteDatabase DB = getReadableDatabase();
        String[] columns = {PATIENT_CLN_INSURANCE};
        String selection = "NumberInsurance=? AND Password=? ";
        String[] selectionArgs = {insurance,password};
        Cursor cursor = DB.query(TABLE_NAME,columns,selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        close();
        if(count > 0){
            return true;
        } else {
            return false;
        }
    }
    public boolean insert_reserved(Doctor doctor){
        SQLiteDatabase DB = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESERVED_CLN_DOCTOR,doctor.getName_dr());
        values.put(RESERVED_CLN_APPOINTMENT,doctor.getAppointment());
        long result= DB.insert(TABLE_NAME2,null,values);

        return result !=-1;
    }
    public ArrayList<Doctor> getAllReserved(){
        ArrayList<Doctor> doctors =new ArrayList<>();
        SQLiteDatabase DB = getReadableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        if(cursor!=null&&cursor.moveToFirst()){
            do {
                String name_dr = cursor.getString(cursor.getColumnIndex(RESERVED_CLN_DOCTOR));
                String appointment = cursor.getString(cursor.getColumnIndex(RESERVED_CLN_APPOINTMENT));

                Doctor doctor =new Doctor(name_dr,appointment);
                doctors.add(doctor);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return  doctors;
    }
    public boolean delete (int id){
        SQLiteDatabase DB = getReadableDatabase();
        String whereClause = RESERVED_CLN_ID + " =? ";
        String[] whereArgs = {String.valueOf(id)};
        int count = DB.delete(TABLE_NAME2, whereClause, whereArgs);
        return count > 0;
    }
//    public boolean insert_reserved(Doctor doctor){
//        SQLiteDatabase DB = getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(RESERVED_CLN_DOCTOR,doctor.getName());
//        values.put(RESERVED_CLN_APPOINTMENT,doctor.getAppointment());
//        long result= DB.insert(TABLE_NAME2,null,values);
//        return result !=-1;
//    }

}
