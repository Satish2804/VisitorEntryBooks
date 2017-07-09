package com.visitorentrybook.model.LogOut;
/*
 * Created by Satish on 22-05-2017.
 */

public class LogoutRequest {
    private String vSecurityId;
    private String sessionId;

    public String getVSecurityId() {
        return vSecurityId;
    }

    public void setVSecurityId(String vSecurityId) {
        this.vSecurityId = vSecurityId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
