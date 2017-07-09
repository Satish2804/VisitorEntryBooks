package com.visitorentrybook.activities.splash;
/*
 * Created by Satish on 22-04-2017.
 */

import android.content.Context;

import com.visitorentrybook.app.VisitorEntryBook;

public class SplashPresenterImpl implements SplashPresenter{

    public SplashPresenterImpl(Context context) {
        ((VisitorEntryBook) context).getAppComponent().inject(this);
    }
}
