package com.visitorentrybook.model.Prefrences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/*
 * Created by Satish on 14-12-2016.
 */

public class GuardDetailsPrefs {
    private SharedPreferences prefs;

    public GuardDetailsPrefs(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserdetails(String sId, String sName, String sEmail, String sPassword,
                               String sPhone, String sPhoto, String sAddress, String addedAdmin, String sStatus,
                               String sLastUpdatedDate, String sCreatedOn, String sSessionId) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sId", sId);
        edit.putString("sName", sName);
        edit.putString("sEmail", sEmail);
        edit.putString("sPassword", sPassword);
        edit.putString("sPhone", sPhone);
        edit.putString("sPhoto", sPhoto);
        edit.putString("sAddress", sAddress);
        edit.putString("addedAdmin", addedAdmin);
        edit.putString("sStatus", sStatus);
        edit.putString("sLastUpdatedDate", sLastUpdatedDate);
        edit.putString("sCreatedOn", sCreatedOn);
        edit.putString("sSessionId", sSessionId);
        edit.apply();
    }


    public String getSId() {
        return prefs.getString("sId", "");
    }

    public void putSId(String sId) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sId", sId).apply();
    }

    public String getSname() {
        return prefs.getString("sName", null);
    }

    public void putSname(String sName) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sName", sName).apply();
    }

    public String getSemail() {
        return prefs.getString("sEmail", "");
    }

    public void putSemail(String sEmail) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sEmail", sEmail).apply();
    }

    public String getSpassword() {
        return prefs.getString("sPassword", "");
    }

    public void putSpassword(String sPassword) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sPassword", sPassword).apply();
    }

    public String getSphoto() {
        return prefs.getString("sPhoto", "");
    }

    public void putSphoto(String sPhoto) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sPhoto", sPhoto).apply();
    }

    public String getSaddress() {
        return prefs.getString("sAddress", "");
    }

    public void putSaddress(String sAddress) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sAddress", sAddress).apply();
    }

    public String getAddedAdmin() {
        return prefs.getString("addedAdmin", "");
    }

    public void putAddedAdmin(String addedAdmin) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("addedAdmin", addedAdmin).apply();
    }

    public String getSstatus() {
        return prefs.getString("sStatus", "");
    }

    public void putSstatus(String sStatus) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sStatus", sStatus).apply();
    }

    public String getSlastUpdatedDate() {
        return prefs.getString("sLastUpdatedDate", "");
    }

    public void putSlastUpdatedDate(String sLastUpdatedDate) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sLastUpdatedDate", sLastUpdatedDate).apply();
    }

    public String getScreatedOn() {
        return prefs.getString("sCreatedOn", "");
    }

    public void putScreatedOn(String sCreatedOn) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sCreatedOn", sCreatedOn).apply();
    }

    public String getSsessionId() {
        return prefs.getString("sSessionId", "");
    }

    public void putSsessionId(String sSessionId) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("sSessionId", sSessionId).apply();
    }

    public SharedPreferences getSDetails() {
        return prefs;
    }

    public void removeSDetails() {
        prefs.edit().clear().apply();
    }
}
