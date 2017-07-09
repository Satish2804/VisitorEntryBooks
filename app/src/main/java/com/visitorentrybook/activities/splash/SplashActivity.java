package com.visitorentrybook.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.visitorentrybook.R;
import com.visitorentrybook.activities.home.HomeActivity;
import com.visitorentrybook.activities.login.LoginActivity;
import com.visitorentrybook.model.Prefrences.GuardDetailsPrefs;

public class SplashActivity extends AppCompatActivity implements SplashView {
    GuardDetailsPrefs guardDetailsPrefs;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        guardDetailsPrefs = new GuardDetailsPrefs(this);
        runnable = new Runnable() {
            @Override
            public void run() {
                if (guardDetailsPrefs.getSstatus().equals("1")) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        };
        new Handler().postDelayed(runnable, 1000);
    }
}
