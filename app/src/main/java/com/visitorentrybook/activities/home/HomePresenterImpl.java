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

package com.visitorentrybook.activities.home;

import android.content.Context;
import android.content.Intent;

import com.facebook.accountkit.ui.LoginType;
import com.visitorentrybook.app.VisitorEntryBook;
import com.visitorentrybook.model.CheckIn.CheckInVerifyVisitorRequest;
import com.visitorentrybook.model.CheckIn.CheckInVerifyVisitorResponse;
import com.visitorentrybook.model.CheckIn.Datum;
import com.visitorentrybook.model.LogOut.LogoutRequest;
import com.visitorentrybook.model.LogOut.LogoutResponse;
import com.visitorentrybook.network.VisitorEntryBookApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenterImpl implements HomePresenter {

    private HomeView view;
    private Boolean result;

    @Inject
    VisitorEntryBookApi Api;

    @Inject
    VisitorEntryBookApi usdaApi;

    public HomePresenterImpl(Context context) {
        ((VisitorEntryBook) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(HomeView view) {
        this.view = view;
    }

    @Override
    public Boolean onCheckinVerifyVisitor(String phone, final CheckInVerifyVisitorRequest checkInVerifyVisitorRequest) {
        view.showLoading();
        usdaApi.getCheckinVerifyVisitorResponse(phone, checkInVerifyVisitorRequest).enqueue(new Callback<CheckInVerifyVisitorResponse>() {
            @Override
            public void onResponse(Call<CheckInVerifyVisitorResponse> call, Response<CheckInVerifyVisitorResponse> response) {
                view.hideLoading();
                CheckInVerifyVisitorResponse checkInVerifyVisitorResponse = response.body();
                if (checkInVerifyVisitorResponse != null) {
                    if (checkInVerifyVisitorResponse.getResult() == 0) {
                        view.hideLoading();
                        Datum datum = checkInVerifyVisitorResponse.getData().get(0);
                        view.onCheckInSuccess(datum);
                        result= true;
                    } else {
                        view.hideLoading();
                        view.verifyVisitorNum(LoginType.PHONE);
                        result= false;
                    }
                }

            }

            @Override
            public void onFailure(Call<CheckInVerifyVisitorResponse> call, Throwable t) {
                view.hideLoading();
                result= false;
            }
        });
        return result;
    }

    @Override
    public void onLogOut(final LogoutRequest logoutRequest) {
        view.showLoading();
        usdaApi.getGuardLogout(logoutRequest).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                view.hideLoading();
                LogoutResponse logoutResponse = response.body();
                if (logoutResponse != null) {
                    if (logoutResponse.getResult() == 0) {
                        view.showLoading();

                    } else {
                        view.showErrorMessage(logoutResponse.getMessage());
                    }
                } else {
                    view.showErrorMessage(logoutResponse.getMessage());
                }

            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                view.hideLoading();
            }
        });
    }


}
