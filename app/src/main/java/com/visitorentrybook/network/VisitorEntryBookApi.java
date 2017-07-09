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

package com.visitorentrybook.network;

import com.visitorentrybook.app.Constants;
import com.visitorentrybook.model.CheckIn.CheckInRequest;
import com.visitorentrybook.model.CheckIn.CheckInResponse;
import com.visitorentrybook.model.CheckIn.CheckInVerifyVisitorRequest;
import com.visitorentrybook.model.CheckIn.CheckInVerifyVisitorResponse;
import com.visitorentrybook.model.CheckOut.CheckOutRequest;
import com.visitorentrybook.model.CheckOut.CheckOutResponse;
import com.visitorentrybook.model.LogOut.LogoutRequest;
import com.visitorentrybook.model.LogOut.LogoutResponse;
import com.visitorentrybook.model.Login.LogInRequest;
import com.visitorentrybook.model.Login.LogInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface VisitorEntryBookApi {

  @Headers("Content-Type:application/json")
  @POST("LoginValidate.php")
  Call<CheckInResponse> getCheckinResponse();

  @Headers("Content-Type:application/json")
  @POST("LoginValidate.php")
  Call<LogInResponse> getLoginResponse(@Query("S_Email") String loginId, @Query("S_Password")String password, @Body LogInRequest logInRequest);

  @Headers("Content-Type:application/json")
  @POST("GetCheckInDetails.php")
  Call<CheckInVerifyVisitorResponse> getCheckinVerifyVisitorResponse(@Query("V_Phone") String phone, @Body CheckInVerifyVisitorRequest checkInVerifyVisitorRequest);

  @Headers("Content-Type:application/json")
  @POST("Logout.php")
  Call<LogoutResponse> getGuardLogout( @Body LogoutRequest logoutRequest);

  @Headers("Content-Type:application/json")
  @POST("CheckOut.php")
  Call<CheckOutResponse> getCheckOutResponse( @Body CheckOutRequest checkOutRequest);

}
