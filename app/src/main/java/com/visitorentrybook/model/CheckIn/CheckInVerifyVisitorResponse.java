package com.visitorentrybook.model.CheckIn;
/*
 * Created by Satish on 22-05-2017.
 */

import java.util.List;

public class CheckInVerifyVisitorResponse {
    private Integer result;
    private List<Datum> data = null;
    private String message;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
