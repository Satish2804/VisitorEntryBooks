/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.visitorentrybook.model.CheckIn;

public class CheckInRequest {


    private String vSecurityId;
    private String vName;
    private String vPhoto;
    private String vPhone;
    private String vEmail;
    private String vCompanyName;
    private String vWhomToMeet;
    private String vPurposeOfMeet;
    private String vProofType;
    private String vProofPhoto;
    private String vDeviceId;
    private String vDevicePhoto;
    private String vCheckInTime;
    private String vCheckOutTime;
    private String vStatus;
    private String sessionId;

    public String getVSecurityId() {
        return vSecurityId;
    }

    public void setVSecurityId(String vSecurityId) {
        this.vSecurityId = vSecurityId;
    }

    public String getVName() {
        return vName;
    }

    public void setVName(String vName) {
        this.vName = vName;
    }

    public String getVPhoto() {
        return vPhoto;
    }

    public void setVPhoto(String vPhoto) {
        this.vPhoto = vPhoto;
    }

    public String getVPhone() {
        return vPhone;
    }

    public void setVPhone(String vPhone) {
        this.vPhone = vPhone;
    }

    public String getVEmail() {
        return vEmail;
    }

    public void setVEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getVCompanyName() {
        return vCompanyName;
    }

    public void setVCompanyName(String vCompanyName) {
        this.vCompanyName = vCompanyName;
    }

    public String getVWhomToMeet() {
        return vWhomToMeet;
    }

    public void setVWhomToMeet(String vWhomToMeet) {
        this.vWhomToMeet = vWhomToMeet;
    }

    public String getVPurposeOfMeet() {
        return vPurposeOfMeet;
    }

    public void setVPurposeOfMeet(String vPurposeOfMeet) {
        this.vPurposeOfMeet = vPurposeOfMeet;
    }

    public String getVProofType() {
        return vProofType;
    }

    public void setVProofType(String vProofType) {
        this.vProofType = vProofType;
    }

    public String getVProofPhoto() {
        return vProofPhoto;
    }

    public void setVProofPhoto(String vProofPhoto) {
        this.vProofPhoto = vProofPhoto;
    }

    public String getVDeviceId() {
        return vDeviceId;
    }

    public void setVDeviceId(String vDeviceId) {
        this.vDeviceId = vDeviceId;
    }

    public String getVDevicePhoto() {
        return vDevicePhoto;
    }

    public void setVDevicePhoto(String vDevicePhoto) {
        this.vDevicePhoto = vDevicePhoto;
    }

    public String getVCheckInTime() {
        return vCheckInTime;
    }

    public void setVCheckInTime(String vCheckInTime) {
        this.vCheckInTime = vCheckInTime;
    }

    public String getVCheckOutTime() {
        return vCheckOutTime;
    }

    public void setVCheckOutTime(String vCheckOutTime) {
        this.vCheckOutTime = vCheckOutTime;
    }

    public String getVStatus() {
        return vStatus;
    }

    public void setVStatus(String vStatus) {
        this.vStatus = vStatus;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
