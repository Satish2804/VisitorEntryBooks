package com.visitorentrybook.activities.camera;
/*
 * Created by Satish on 08-05-2017.
 */

import android.content.Context;

import com.visitorentrybook.activities.home.HomeView;
import com.visitorentrybook.app.VisitorEntryBook;
import com.visitorentrybook.network.VisitorEntryBookApi;

import javax.inject.Inject;

public class CameraPresenterImpl implements CameraPresenter {
    private CameraView view;

    @Inject
    VisitorEntryBookApi Api;


    public CameraPresenterImpl(Context context) {
        ((VisitorEntryBook) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(CameraView view) {
        this.view = view;
    }

}
