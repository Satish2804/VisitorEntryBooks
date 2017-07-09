package com.visitorentrybook.model.Database;
/*
 * Created by Satish on 21-05-2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.visitorentrybook.model.Database.DbContract.SecurityTableFields.SECURITY_DETAILS_TABLE;
import static com.visitorentrybook.model.Database.DbContract.VisitorTableFields.VISITOR_DETAILS_TABLE;

public class DbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "VisitorAndGuard.db";

    private static final String SQL_DELETE_VISITOR_ENTRIES = "DROP TABLE IF EXISTS " + VISITOR_DETAILS_TABLE;
    private static final String SQL_DELETE_SECURITY_ENTRIES = "DROP TABLE IF EXISTS " + SECURITY_DETAILS_TABLE;
    private static final String SQL_CREATE_SECURITY_DETAILS_TABLE = "CREATE TABLE " + SECURITY_DETAILS_TABLE + "("
            + DbContract.SecurityTableFields.S_Id + " INTEGER PRIMARY KEY,"
            + DbContract.SecurityTableFields.S_Name + " TEXT,"
            + DbContract.SecurityTableFields.S_Email + " TEXT,"
            + DbContract.SecurityTableFields.S_Phone + " TEXT,"
            + DbContract.SecurityTableFields.S_Photo + " BLOB,"
            + DbContract.SecurityTableFields.S_Address + " TEXT,"
            + DbContract.SecurityTableFields.Added_AdminId + " INTEGER,"
            + DbContract.SecurityTableFields.Created_Date + " INTEGER,"
            + DbContract.SecurityTableFields.S_Status + " TEXT,"
            + DbContract.SecurityTableFields.Last_UpdatedDate + " INTEGER,"
            + DbContract.SecurityTableFields.S_Password + " TEXT,"
            + ");";

    private static final String SQL_CREATE_VISITOR_DETAILS_TABLE = "CREATE TABLE " + VISITOR_DETAILS_TABLE + "("
            + DbContract.VisitorTableFields.V_Id + " INTEGER PRIMARY KEY,"
            + DbContract.VisitorTableFields.V_SecurityId + " INTEGER,"
            + DbContract.VisitorTableFields.V_Name + " TEXT,"
            + DbContract.VisitorTableFields.V_Photo + " BLOB,"
            + DbContract.VisitorTableFields.V_Phone + " TEXT,"
            + DbContract.VisitorTableFields.V_Email + " TEXT,"
            + DbContract.VisitorTableFields.V_CompanyName + " TEXT,"
            + DbContract.VisitorTableFields.V_ComingFrom + " TEXT,"
            + DbContract.VisitorTableFields.V_EmployeeName + " TEXT,"
            + DbContract.VisitorTableFields.V_EmployeeEmail + " TEXT,"
            + DbContract.VisitorTableFields.V_EmployeePhone + " INTEGER,"
            + DbContract.VisitorTableFields.V_ProofType + " TEXT,"
            + DbContract.VisitorTableFields.V_ProofId + " TEXT,"
            + DbContract.VisitorTableFields.V_DeviceName + " TEXT,"
            + DbContract.VisitorTableFields.V_DeviceCompany + " TEXT,"
            + DbContract.VisitorTableFields.V_DeviceNumber + " TEXT,"
            + DbContract.VisitorTableFields.V_CheckInTime + " INTEGER,"
            + DbContract.VisitorTableFields.V_CheckOutTime + " INTEGER,"
            + DbContract.VisitorTableFields.V_HoursOfVisit + " INTEGER,"
            + DbContract.VisitorTableFields.V_Status + " TEXT,"
            + ");";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SECURITY_DETAILS_TABLE);
        db.execSQL(SQL_CREATE_VISITOR_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SECURITY_ENTRIES);
        db.execSQL(SQL_DELETE_VISITOR_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
