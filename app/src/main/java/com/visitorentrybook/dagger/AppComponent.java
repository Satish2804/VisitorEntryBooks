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

package com.visitorentrybook.dagger;

import com.visitorentrybook.activities.camera.CameraActivity;
import com.visitorentrybook.activities.camera.CameraPresenterImpl;
import com.visitorentrybook.activities.checkIn.CheckInActivity;
import com.visitorentrybook.activities.checkIn.CheckInPresenterImpl;
import com.visitorentrybook.activities.home.HomeActivity;
import com.visitorentrybook.activities.home.HomePresenterImpl;
import com.visitorentrybook.activities.login.LoginActivity;
import com.visitorentrybook.activities.login.LoginPresenterImpl;
import com.visitorentrybook.activities.splash.SplashActivity;
import com.visitorentrybook.activities.splash.SplashPresenterImpl;
import com.visitorentrybook.model.Login.LogInRequest;
import com.visitorentrybook.model.Prefrences.GuardDetailsPrefs;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(HomeActivity target);

    void inject(HomePresenterImpl target);

    void inject(LoginActivity target);

    void inject(LoginPresenterImpl target);

    void inject(SplashActivity target);

    void inject(SplashPresenterImpl target);

    void inject(CheckInActivity target);

    void inject(CheckInPresenterImpl target);

    void inject(CameraActivity target);

    void inject(CameraPresenterImpl target);

}
