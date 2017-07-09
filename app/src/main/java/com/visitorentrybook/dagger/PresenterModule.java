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

import android.content.Context;


import com.visitorentrybook.activities.camera.CameraPresenter;
import com.visitorentrybook.activities.camera.CameraPresenterImpl;
import com.visitorentrybook.activities.checkIn.CheckInActivity;
import com.visitorentrybook.activities.checkIn.CheckInPresenter;
import com.visitorentrybook.activities.checkIn.CheckInPresenterImpl;
import com.visitorentrybook.activities.login.LoginPresenter;
import com.visitorentrybook.activities.login.LoginPresenterImpl;
import com.visitorentrybook.activities.home.HomePresenter;
import com.visitorentrybook.activities.home.HomePresenterImpl;
import com.visitorentrybook.activities.splash.SplashPresenter;
import com.visitorentrybook.activities.splash.SplashPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

  @Provides
  @Singleton
  LoginPresenter provideLoginPresenter(Context context) {
    return new LoginPresenterImpl(context);
  }

  @Provides
  @Singleton
  SplashPresenter provideSplashPresenter(Context context) {
    return new SplashPresenterImpl(context);
  }

  @Provides
  @Singleton
  HomePresenter provideHomePresenter(Context context) {
    return new HomePresenterImpl(context);
  }
  @Provides
  @Singleton
  CameraPresenter provideCameraPresenter(Context context) {
    return new CameraPresenterImpl(context);
  }
  @Provides
  @Singleton
  CheckInPresenter provideCheckInPresenter(Context context) {
    return new CheckInPresenterImpl(context);
  }
}
