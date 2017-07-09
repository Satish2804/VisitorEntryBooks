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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.visitorentrybook.R;
import com.visitorentrybook.activities.home.HomeActivity;
import com.visitorentrybook.app.VisitorEntryBook;
import com.visitorentrybook.model.Login.LogInRequest;
import com.visitorentrybook.widgets.VbEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private static final String TAG = "LoginActivity";
    @Inject
    LoginPresenter presenter;

    @BindView(R.id.input_email)
    VbEditText editText_email;

    @BindView(R.id.input_password)
    VbEditText editText_password;

    @BindView(R.id.btn_login)
    AppCompatButton button_login;

    LogInRequest logInRequest;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((VisitorEntryBook) getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);
        presenter.setView(this);


        button_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.login();
                    }
                });

    }


    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void login() {
        Log.d(TAG, "Login");

        if (!validateLogin()) {
            onLoginFailed();
            return;
        }

        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        logInRequest = new LogInRequest();
        logInRequest.setSEmail(email);
        logInRequest.setSPassword(password);
        presenter.loginCall(email, password, logInRequest);
    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginSuccess() {

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    public Boolean validateLogin() {
        boolean valid = true;

        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText_email.setError("enter a valid email address");
            valid = false;
        } else {
            editText_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editText_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editText_password.setError(null);
        }

        return valid;

    }
}
