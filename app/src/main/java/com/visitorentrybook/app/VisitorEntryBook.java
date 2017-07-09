

package com.visitorentrybook.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.visitorentrybook.dagger.AppComponent;
import com.visitorentrybook.dagger.AppModule;
import com.visitorentrybook.dagger.DaggerAppComponent;



public class VisitorEntryBook extends Application {

  private AppComponent appComponent;

  public AppComponent getAppComponent() {
    return appComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
    appComponent = initDagger(this);
  }

  protected AppComponent initDagger(VisitorEntryBook application) {
    return DaggerAppComponent.builder()
        .appModule(new AppModule(application))
        .build();
  }
}
