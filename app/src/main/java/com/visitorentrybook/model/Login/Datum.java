package com.visitorentrybook.model.Login;
/*
 * Created by Satish on 14-05-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("S_Id")
    @Expose
    private String sId;
    @SerializedName("S_Name")
    @Expose
    private String sName;
    @SerializedName("S_Email")
    @Expose
    private String sEmail;
    @SerializedName("S_Password")
    @Expose
    private String sPassword;
    @SerializedName("S_Phone")
    @Expose
    private String sPhone;
    @SerializedName("S_Photo")
    @Expose
    private String sPhoto;
    @SerializedName("S_Address")
    @Expose
    private String sAddress;
    @SerializedName("Added_AdminId")
    @Expose
    private String addedAdminId;
    @SerializedName("S_Status")
    @Expose
    private String sStatus;
    @SerializedName("Last_UpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("S_CreatedOn")
    @Expose
    private String sCreatedOn;
    @SerializedName("session_id")
    @Expose
    private String sessionId;

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getSPhone() {
        return sPhone;
    }

    public void setSPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getSPhoto() {
        return sPhoto;
    }

    public void setSPhoto(String sPhoto) {
        this.sPhoto = sPhoto;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getAddedAdminId() {
        return addedAdminId;
    }

    public void setAddedAdminId(String addedAdminId) {
        this.addedAdminId = addedAdminId;
    }

    public String getSStatus() {
        return sStatus;
    }

    public void setSStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getSCreatedOn() {
        return sCreatedOn;
    }

    public void setSCreatedOn(String sCreatedOn) {
        this.sCreatedOn = sCreatedOn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
