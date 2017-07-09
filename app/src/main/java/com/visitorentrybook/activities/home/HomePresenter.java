
package com.visitorentrybook.activities.home;

import com.visitorentrybook.model.CheckIn.CheckInVerifyVisitorRequest;
import com.visitorentrybook.model.LogOut.LogoutRequest;

public interface HomePresenter {
  void setView(HomeView view);

  Boolean onCheckinVerifyVisitor(String phone, CheckInVerifyVisitorRequest checkInVerifyVisitorRequest);


  void onLogOut(LogoutRequest logoutRequest);
}
