package com.visitorentrybook.activities.checkIn;
/*
 * Created by Satish on 22-04-2017.
 */

import android.content.Context;

import com.visitorentrybook.app.VisitorEntryBook;

public class CheckInPresenterImpl implements CheckInPresenter {
    private CheckInView checkInView;

    public CheckInPresenterImpl(Context context) {
        ((VisitorEntryBook) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(CheckInView checkInView) {
        this.checkInView = checkInView;
    }

    @Override
    public void checkIn() {
        checkInView.checkIn();
    }
}
