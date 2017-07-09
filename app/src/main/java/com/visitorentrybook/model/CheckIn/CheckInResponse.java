package com.visitorentrybook.model.CheckIn;
/*
 * Created by Satish on 22-04-2017.
 */

public class CheckInResponse {
    private Integer result;
    private String message;
    private String sessionStatus;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

}
