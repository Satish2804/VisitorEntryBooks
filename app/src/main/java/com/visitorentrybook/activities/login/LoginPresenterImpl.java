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

package com.visitorentrybook.activities.login;

import android.content.Context;

import com.visitorentrybook.app.VisitorEntryBook;
import com.visitorentrybook.model.Login.Datum;
import com.visitorentrybook.model.Login.LogInRequest;
import com.visitorentrybook.model.Login.LogInResponse;
import com.visitorentrybook.model.Prefrences.GuardDetailsPrefs;
import com.visitorentrybook.network.VisitorEntryBookApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private Context context;

    @Inject
    VisitorEntryBookApi usdaApi;

    GuardDetailsPrefs guardDetailsPrefs;

    public LoginPresenterImpl(Context context) {
        this.context = context;
        guardDetailsPrefs= new GuardDetailsPrefs(context);
        ((VisitorEntryBook) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(LoginView view) {
        this.view = view;
    }

    @Override
    public void login() {
        view.login();
    }


    @Override
    public void loginCall(String email, String pass, final LogInRequest logInRequest) {
        view.showLoading();
        usdaApi.getLoginResponse(email, pass, logInRequest).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                view.hideLoading();
                LogInResponse logInResponse = response.body();
                if (logInResponse != null) {
                    if (logInResponse.getResult() == 0) {
                        view.onLoginSuccess();
                        Datum datum = logInResponse.getData().get(0);
                        guardDetailsPrefs.setUserdetails(datum.getSId(), datum.getSName(), datum.getSEmail(), datum.getSPassword(), datum.getSPhone(),
                                datum.getSPhoto(), datum.getSAddress(), datum.getAddedAdminId(), "1", datum.getLastUpdatedDate(), datum.getSCreatedOn(), datum.getSessionId());
                    } else {
                        view.showErrorMessage(logInResponse.getMessage());
                    }
                } else {
                    view.showErrorMessage(logInResponse.getMessage());
                }

            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {view.hideLoading();}
        });
    }

}