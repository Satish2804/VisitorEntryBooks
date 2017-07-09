package com.visitorentrybook.model.Database;
/*
 * Created by Satish on 22-05-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

public class DbOperations {
    private static final String TAG = DbHelper.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;
    private DbHelper customSQLiteHelper;
    private int response_Code = 0;


    public DbOperations(Context context) {
        customSQLiteHelper = new DbHelper(context);
    }

    public void dbOpen() {
        sqLiteDatabase = customSQLiteHelper.getWritableDatabase();
    }

    public void dbClose() {
        sqLiteDatabase.close();
    }

    public HashMap<String, String> GetSecurityDetails(
            String Id) {
        HashMap<String, String> SecurityDetails = new HashMap<>();
        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase
                    .rawQuery(
                            "select Latitude, Longitude from " + DbContract.SecurityTableFields.SECURITY_DETAILS_TABLE + " where S_Id ='"
                                    + Id + "'", null);
            boolean cursorStatus = cursor.moveToFirst();
            if (cursorStatus) {
                do {
                    SecurityDetails
                            .put("Latitude", cursor.getString(cursor
                                    .getColumnIndex("Latitude")));
                    SecurityDetails.put("Longitude", cursor.getString(cursor
                            .getColumnIndex("Longitude")));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(e.toString(), this.getClass().getSimpleName());
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return SecurityDetails;
    }

    public int insertSecurityDetails(String S_Id, String S_Name,
                                     String S_Email, String S_Phone, String Added_AdminId,
                                     String S_Photo, String S_Address,
                                     String phoneIceAreaCode, String phoneIceCountryCode,
                                     String phoneIceNumber, String Created_Date,
                                     String Last_UpdatedDate, String S_Password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("S_Id", S_Id);
        contentValues.put("S_Name", S_Name);
        contentValues.put("S_Email", S_Email);
        contentValues.put("S_Phone", S_Phone);
        contentValues.put("S_Photo", S_Photo);
        contentValues.put("S_Address", S_Address);
        contentValues.put("Added_AdminId", Added_AdminId);
        contentValues.put("Created_Date", Created_Date);
        contentValues.put("Last_UpdatedDate", Last_UpdatedDate);
        contentValues.put("S_Password", S_Password);
        sqLiteDatabase.insert(DbContract.SecurityTableFields.SECURITY_DETAILS_TABLE, null,
                contentValues);
        Cursor cursor = sqLiteDatabase.rawQuery("select _id from "
                + DbContract.SecurityTableFields.SECURITY_DETAILS_TABLE, null);
        if (cursor != null) {
            cursor.moveToFirst();
            response_Code = cursor.getInt(0);
            cursor.close();
        }
        return response_Code;
    }

    public int insertVistorDetails(String V_Id, String V_SecurityId,
                                   String V_Name, String V_Photo, String V_Phone,
                                   String V_Email, String V_CompanyName,
                                   String V_ComingFrom, String V_ProofType,
                                   String V_ProofId, String Created_Date,
                                   String Last_UpdatedDate, String S_Password,
                                   String V_DeviceName, String V_DeviceCompany,
                                   String V_DeviceNumber, String V_CheckInTime,
                                   String V_CheckOutTime, String V_HoursOfVisit,
                                   String V_Status) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("V_Id", V_Id);
        contentValues.put("V_SecurityId", V_SecurityId);
        contentValues.put("V_Name", V_Name);
        contentValues.put("V_Photo", V_Photo);
        contentValues.put("V_Email", V_Email);
        contentValues.put("V_CompanyName", V_CompanyName);
        contentValues.put("V_Phone", V_Phone);
        contentValues.put("Created_Date", Created_Date);
        contentValues.put("Last_UpdatedDate", Last_UpdatedDate);
        contentValues.put("S_Password", S_Password);
        contentValues.put("V_ComingFrom", V_ComingFrom);
        contentValues.put("V_ProofType", V_ProofType);
        contentValues.put("V_ProofId", V_ProofId);
        contentValues.put("V_DeviceName", V_DeviceName);
        contentValues.put("V_DeviceCompany", V_DeviceCompany);
        contentValues.put("V_DeviceNumber", V_DeviceNumber);
        contentValues.put("V_ProofType", V_ProofType);
        contentValues.put("V_CheckInTime", V_CheckInTime);
        contentValues.put("V_CheckOutTime", V_CheckOutTime);
        contentValues.put("V_HoursOfVisit", V_HoursOfVisit);
        contentValues.put("V_Status", V_Status);
        sqLiteDatabase.insert(DbContract.VisitorTableFields.VISITOR_DETAILS_TABLE, null,
                contentValues);
        Cursor cursor = sqLiteDatabase.rawQuery("select _id from "
                + DbContract.VisitorTableFields.VISITOR_DETAILS_TABLE, null);
        if (cursor != null) {
            cursor.moveToFirst();
            response_Code = cursor.getInt(0);
            cursor.close();
        }
        return response_Code;
    }
}
